import { useContext, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { ThreadsContext } from "../contexts/ThreadsContext";
import { ThreadParams } from "../helpers/interfaces";
import MessageItem from "./MessageItem";
import OpacityPanel from "./OpacityPanel";

const ThreadPage = () => {
    const { getThreadById } = useContext(ThreadsContext)
    const { id } = useParams();

    const [thread, setThread] = useState<ThreadParams | null>(null);

    useEffect(() => {
        const fetchThread = async (id: string) => {
            const data = await getThreadById(id);
            if (data !== null) setThread(data);
        }
        if (id !== undefined) fetchThread(id);
    }, [id, getThreadById]);

    return (
        <OpacityPanel>
            <div className="w-full flex flex-col gap-8">
                {thread ? (
                    <MessageItem msg={{
                        id: thread.id,
                        nickname: thread.nickname,
                        title: thread.title,
                        content: thread.content,
                        image_url: thread.image_url,
                        created_at: thread.created_at,
                        message_number: 0,
                        is_thread: true
                    }} />
                ) : (
                    <div>Loading thread...</div>
                )}
                {thread?.messages?.length ? (
                    thread.messages.map((msg, key) => (
                        <div key={key}>
                            <MessageItem msg={msg} />
                        </div>
                    ))
                ) : (
                    <p className="ml-4 mt-4 text-xl">no replys</p>
                )}
            </div>
        </OpacityPanel>
    )
}

export default ThreadPage