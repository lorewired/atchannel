import { MessageParams } from "../helpers/interfaces"

const MessageItem = ({ msg }: { msg: MessageParams }) => {
  const date = new Date(msg.created_at);
  let week_day;
  switch (date.getDay()) {
    case 0: week_day = "Sun"; break;
    case 1: week_day = "Mon"; break;
    case 2: week_day = "Tue"; break;
    case 3: week_day = "Wed"; break;
    case 4: week_day = "Thu"; break;
    case 5: week_day = "Fri"; break;
    case 6: week_day = "Sat"; break;
  }
  const zmin: boolean = date.getMinutes() < 10;
  const zseg: boolean = date.getSeconds() < 10;
  const date_view = `${date.getFullYear()}/${date.getMonth()}/${date.getDate()}(${week_day}) ${date.getHours()}:${zmin ? "0" : ""}${date.getMinutes()}:${zseg ? "0" : ""}${date.getSeconds()} ID:${msg.is_thread ? "Thread OP" : msg.visual_id}`;

  return (
    <div className="w-[800px] ml-4 [&>*]:text-[20px]">
      <div className="flex gap-3">
        {msg.message_number} : <span className={`text-[20px] ${msg.is_thread ? "text-blue-600" : "text-green-600"}`}>{msg.nickname}</span> : {date_view}
      </div>
      <div className="ml-16 mt-3 [&>*]:text-[20px]">
        {msg.title !== undefined && <li className="mb-8">{msg.title}</li>}
        <p>{msg.content}</p>
      </div>
    </div>
  )
}

export default MessageItem