import { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import { ThreadsContext } from "../contexts/ThreadsContext";
import { CreateThreadInputParams } from "../helpers/interfaces";
import OpacityPanel from "./OpacityPanel";

const CreateThreadPanel = () => {
    const [nickname, setNickname] = useState("Anonymous"); 
    const [title, setTitle] = useState(""); 
    const [content, setContent] = useState(""); 
    const [image_url, setImage_url] = useState(""); 

    const { postThread } = useContext(ThreadsContext);

    const navigate = useNavigate();

    const redirectTo = (path: string) => {
        setNickname("");
        setTitle("");
        setContent("");
        setImage_url("");
        navigate(path);
    }

    const handlePostThread = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const res = await postThread({
            nickname,
            content,
            title,
            image_url
        });
        switch (res) {
        case 201:
            setTimeout(() => redirectTo(`/thread/${title}`), 500);
            break;
        case 400:
            alert(`Thread with title "${title}" already exists.`);
            break;
        default:
            alert("Something went wrong.");
            break;
        }
    }

    return (
        <OpacityPanel>
            <form
                onSubmit={e => handlePostThread(e)}
                className="flex flex-col gap-1 items-center"
            >
                <Input
                    inputValue={nickname}
                    setInputValue={setNickname}
                    text="Name"
                    id="nickname"
                />
                <Input
                    inputValue={title}
                    setInputValue={setTitle}
                    text="Title"
                    id="title"
                />
                <Input
                    inputValue={image_url}
                    setInputValue={setImage_url}
                    text="Image URL"
                    id="image_url"
                />
                <Input
                    inputValue={content}
                    setInputValue={setContent}
                    content={true}
                    text="Content"
                    id="content"
                />
                <button
                    className="w-[500px] p-1 border border-gray-700"
                    type="submit"
                >
                    Post
                </button>
            </form>

        </OpacityPanel>
    )
}

const Input = ({ text, id, content, inputValue, setInputValue }: CreateThreadInputParams) => {
    return <div className="w-[500px] flex [&>*]:p-2">
        <label className="w-[30%] border border-gray-700 border-r-transparent" htmlFor={id}>{text}</label>
        {content ?
        <textarea
            value={inputValue}
            placeholder="..."
            required
            maxLength={4000}
            className="border border-gray-700 resize-none w-[70%] h-[200px]"
            id={id}
            onChange={e => setInputValue(e.target.value)}
        /> : 
        <input
            value={inputValue}
            placeholder="..."
            required
            maxLength={id === "image_url" ? 4000 : 100}
            className="w-[70%] border border-gray-700"
            type="text"
            id={id}
            onChange={e => setInputValue(e.target.value)}
        />}
    </div>
}

export default CreateThreadPanel