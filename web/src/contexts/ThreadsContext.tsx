import { createContext, useEffect, useState } from "react";
import { ThreadContextParams, ThreadInputParams, ThreadParams } from "../helpers/interfaces";

export const ThreadsContext = createContext<ThreadContextParams>({
    threads: [],
    postThread: async () => 0,
    getThreadById: async () => null
});

export const ThreadsContextProvider = ({ children }: { children: React.ReactNode }) => {
    const [threads, setThreads] = useState<ThreadParams[]>([]);

    const getAllThreads = async () => {
        const url = "http://localhost:8080/atchannel/api/threads";
        await fetch(url, {
            method: "GET",
            headers: {"Content-Type": "application/json"}
        })  
        .then(res => res.json())
        .then(data => setThreads(data))
        .catch(err => console.error("error getting threads: ", err));
    }
    
    const getThreadById = async (id: string): Promise<ThreadParams | null> => {
        const url = `http://localhost:8080/atchannel/api/threads/${id}`;
        try {
            const res = await fetch(url, {
                method: "GET",
                headers: {"Content-Type": "application/json"}
            });
            const data = await res.json();
            return data;
        } catch (err) {
            console.error(err);
            return null;
        }
    }

    const postThread = async (thread: ThreadInputParams): Promise<number> => {
        const url = "http://localhost:8080/atchannel/api/threads";
        try {
            const res = await fetch(url, {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify(thread)
            });
            return res.status;
        } catch (err) {
            console.error(err);
            return 500;
        }
    }

    const fetchThreads = async () => await getAllThreads();

    useEffect(() => {
        fetchThreads(); 
    }, []);

    return <ThreadsContext.Provider value={{ threads, postThread, getThreadById }}>
        { children }
    </ThreadsContext.Provider>
}