import React, { useState, useEffect } from 'react';
// import img2002 from '../assets/2002.jpg';
// import imgChanges from '../assets/changes.jpg';
// import imgLover from '../assets/lover.jpg';
// import imgSugar from '../assets/sugar.jpg';
// import imgYouth from '../assets/youth.jpg';
// import imgMe from '../assets/me.jpg';
import LpPlayer from '../component/main/LpPlayer.tsx';
import Singleroom from '../component/main/SingleRoom.tsx';
import axios from 'axios';

interface Music {
    albumTitle: string;
    trackId: number;
    trackTitle: string;
    artists: string;
    albumImg: string;
    score: number;
    is_like: boolean;
    youtubeId: string;
}

export interface SelectedMusic {
    trackId: number;
    trackTitle: string;
    artists: string;
    albumImg: string;
    youtubeId: string;
}


const SelectSinglePage: React.FC = () => {
    const [playList, setPlayList] = useState<Music[]>([]);

    useEffect(() => {
    axios.get("https://j10a106.p.ssafy.io/api/singleplay/playlist?type=recent&page=0&size=20")
        .then((Response) => {
            console.log(Response.data)
            setPlayList(Response.data.data.playList);
            console.log(playList);
        })
        .catch((error) => {
            console.error('Error fetching playlist:', error);
        });
    }, []);

    const [selectedMusic, setSeletedMusic] = useState<SelectedMusic>({
        trackId: 0,
        trackTitle: "",
        artists: "",
        albumImg: "",
        youtubeId: "",
    })

    const handleClickButton = (index: number):void => {
        setSeletedMusic({
            trackId: playList[index].trackId,
            trackTitle: playList[index].trackTitle,
            artists: playList[index].artists,
            albumImg: playList[index].albumImg,
            youtubeId: playList[index].youtubeId,
        })
    }
    return (
        <div className="bg-black h-svh w-screen m-0 p-0 flex">
            {/*검색창*/}
            <div className='flex flex-col pt-10 pl-8 '>
            <div className="h-11 w-3/5 rounded-lg bg-gradient-to-r from-[white] via-[#00ffff] to-[#3F4685] p-0.5 relative">
                <div className="flex h-full w-full rounded-lg items-center bg-primary-950 back ">
                    <div className="text-sm text-primary-200 font-thin pl-5 py-2 flex-1">플레이하고 싶은 노래를 검색해보세요!</div>
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="white" className="w-6 h-6 absolute right-4 top-1/2 transform -translate-y-1/2">
                        <path strokeLinecap="round" strokeLinejoin="round" d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z" />
                    </svg>
                </div>
            </div>

            <div className='pt-5 flex flex-row h-5/6'>
                {/* lp판 */}
                <div >
                    <h1 className='text-white font-bold text-xl w-60 pb-6'>싱글 플레이</h1>
                    <LpPlayer trackId={selectedMusic.trackId} title={selectedMusic.trackTitle} img={selectedMusic.albumImg} artists={selectedMusic.artists} youtubeId={selectedMusic.youtubeId} />
                </div>

                    {/* 플레이리스트 목록 */}
                    {/* 자꾸 화면 삐져나와... 고쳐줘...*/}
                <div className='pl-14 w-4/5'>
                    <h1 className=' text-white font-bold text-xl w-60 pb-3'>최근 플레이한 음악</h1>
                    <div className='flex flex-row pb-6 w-full'>
                        <h1 className=' text-white font-thin text-sm w-60 flex-1'>플레이 할 노래를 선택해주세요!</h1>
                        <h1 className=' text-white font-thin text-sm w-60 text-right flex-1 pr-5'>ⓘ 플레이 할 노래를 선택해주세요!</h1>
                    </div>
    
                    <div className="relative flex flex-col overflow-y-auto h-full">
                        <div className='text-white grid grid-cols-3 gap-4 justify-items-start '>
                        {playList && playList.length > 0 ? ( // playList가 비어있지 않은 경우에만 map 함수 호출
                        playList.map((item, index) => (
                            <div key={index} onClick={() => handleClickButton(index)}>
                                <Singleroom album_title={item.albumTitle} title={item.trackTitle} artists={item.artists} img={item.albumImg} is_like={item.is_like} score={item.score} />
                            </div>
                                ))
                            ) : (
                                <div className="text-white w-48">플레이리스트가 비어있습니다.</div>
                            )}
                        </div>
                    </div>
                </div>
            </div>

            </div>
        </div>
    );
};

export default SelectSinglePage;