import React, { useState } from 'react';
// import { Link } from 'react-router-dom';
import InputPassword from './InputPassword';


interface MultiroomProps {
    roomId: number;
    genre: string;
    roomName: string;
    currentUser: number;
    maxUser: number;
    multiPlayImgUrl: string;
    isSecret: boolean;
    password: number;
}

const Multiroom: React.FC<MultiroomProps> = ({
    roomId,
    roomName,
    currentUser,
    maxUser,
    multiPlayImgUrl,
    isSecret,
    password
}) => {
    const [showPasswordModal, setShowPasswordModal] = useState(false);

    const handleEnterRoom = () => {
        if (isSecret) {
            setShowPasswordModal(true);
        } else {
            // Navigate to the room without password check
            window.location.href = `/waitroom/${roomId}`;
        }
    };

    return (
        <div className='relative'>
            <div className='relative text-white bg-primary-800/50 w-52 rounded-lg hover:opacity-50 relative'>
                <img src={multiPlayImgUrl} alt={roomName} className='w-52 h-48 rounded-t-lg '/>
                <div className='flex flex-row'>
                    <div className='pt-2 pl-2'>
                        <div className='text-lg font-extrabold text-white pt-3'>{roomName} </div>
                        <div className='flex flex-row text-sm pb-4 gap-1'>
                            <div className='text-secondary-500'> {currentUser} </div>
                            <div>/</div>
                            <div> {maxUser}</div>
                        </div>
                    </div>  
                    {isSecret ? 
                        <div className='flex rounded-full bg-secondary-200 w-8 h-8 absolute left-3 top-3 items-center justify-center'>
                            <svg xmlns="https://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="purple" className="w-5 h-5">
                                <path fillRule="evenodd" d="M12 1.5a5.25 5.25 0 0 0-5.25 5.25v3a3 3 0 0 0-3 3v6.75a3 3 0 0 0 3 3h10.5a3 3 0 0 0 3-3v-6.75a3 3 0 0 0-3-3v-3c0-2.9-2.35-5.25-5.25-5.25Zm3.75 8.25v-3a3.75 3.75 0 1 0-7.5 0v3h7.5Z" clipRule="evenodd" />
                            </svg>
                        </div> 
                        : 
                        <div className='flex rounded-full bg-secondary-200 w-8 h-8 absolute left-3 top-3 items-center justify-center'>
                            <svg xmlns="https://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="#cbaacb" className="w-5 h-5">
                                <path d="M18 1.5c2.9 0 5.25 2.35 5.25 5.25v3.75a.75.75 0 0 1-1.5 0V6.75a3.75 3.75 0 1 0-7.5 0v3a3 3 0 0 1 3 3v6.75a3 3 0 0 1-3 3H3.75a3 3 0 0 1-3-3v-6.75a3 3 0 0 1 3-3h9v-3c0-2.9 2.35-5.25 5.25-5.25Z" />
                            </svg>
                        </div>}
                </div>
            </div>
            <div className='flex justify-center items-center rounded-full h-9 w-9 bg-white absolute right-2 top-44 text-xs text-center font-bold text-black'>
                <p onClick={handleEnterRoom}>입장</p>
            </div>
            {/* 비밀번호 입력 모달 */}
            {showPasswordModal && (
                <div className="modal">
                    <div className="modal-content">
                        {/* InputPassword 컴포넌트를 모달 내에 렌더링 */}
                        <InputPassword
                            onClose={() => setShowPasswordModal(false)}
                            correctPassword={password} // 멀티룸의 비밀번호 전달
                            roomId={roomId}
                        />
                    </div>
                </div>
            )}
        </div>
    );
};

export default Multiroom;
