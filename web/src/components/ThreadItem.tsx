import { Link } from "react-router-dom"
import { ThreadParams } from "../helpers/interfaces"

const ThreadItem = ({ thread }: { thread: ThreadParams }) => {
    const date = new Date(thread.created_at);
    const posted_at = `${date.getDate()}/${date.getMonth()}/${date.getFullYear()} ${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`;

    let tmp_content = thread.content;

    if (thread.content.length > 100) {
        tmp_content = "";
        for (let i = 0; i < 97; i++)
            tmp_content += thread.content[i];
        tmp_content += "...";
    }
    
    return <div
        className="w-[230px] h-[328px] flex flex-col gap-3 items-center"
    >
        <Link to={`/thread/${thread.id}`}>
            <img
                title={`Posted by ${thread.nickname} in ${posted_at}`}
                className="max-w-[130px] max-h-[130px] thread_shadow"
                src={thread.image_url}
                alt="thread image"
            />
        </Link>
        <h3 className="w-[180px] text-[15px] text-center">
            <span className="font-bold">
                {thread.title}
            </span>
            : <span>{tmp_content}</span>
        </h3>
    </div>
}

export default ThreadItem