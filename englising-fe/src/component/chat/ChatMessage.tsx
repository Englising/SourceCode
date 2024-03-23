import { User } from "../../pages/MultiplayPage";

interface ChatMessageProps {
  user: User;
  message: string;
  profileVisible: boolean;
  myMessage: boolean;
}

function ChatMessage({ user, message, profileVisible, myMessage }: ChatMessageProps) {
  return (
    <div className={`flex gap-2 ${myMessage ? "justify-end" : ""}`}>
      <div className={`w-8 h-8 flex-shrink-0 rounded-full overflow-hidden ${profileVisible ? "" : "hidden"}`}>
        <img src={user.profileImage} />
      </div>
      <div>
        <p className={`mb-1 ${profileVisible ? "" : "hidden"}`}>{user.nickname}</p>
        {myMessage ? (
          <p className={`max-w-52 ms-auto py-1 px-2 bg-gray-800 rounded-lg rounded-tr-sm break-keep`}>{message}</p>
        ) : (
          <p
            className={`max-w-52 py-1 px-2 bg-gray-100 text-gray-900 rounded-lg rounded-tl-sm break-keep ${profileVisible ? "" : "ms-10"}`}
          >
            {message}
          </p>
        )}
      </div>
    </div>
  );
}

export default ChatMessage;