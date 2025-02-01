import { Dispatch, SetStateAction } from "react";

export interface MessageParams {
    id: string;
    visual_id?: string;
    nickname: string;
    title?: string;
    content: string;
    image_url: string;
    created_at: string;
    message_number: number;
    is_thread?: boolean;
}

export interface ThreadParams {
    id: string;
    nickname: string;
    content: string;
    title: string;
    image_url: string;
    total_messages: number;
    created_at: string;
    messages: MessageParams[] | undefined;
}

export interface ThreadContextParams {
    threads: ThreadParams[],
    postThread: (thread: ThreadInputParams) => Promise<number>,
    getThreadById: (id: string) => Promise<ThreadParams | null>
}

export interface CreateThreadInputParams {
    text: string,
    id: string,
    content?: boolean,
    inputValue: string
    setInputValue: Dispatch<SetStateAction<string>>,
}

export interface ThreadInputParams {
    nickname: string;
    content: string;
    title: string;
    image_url: string;
}