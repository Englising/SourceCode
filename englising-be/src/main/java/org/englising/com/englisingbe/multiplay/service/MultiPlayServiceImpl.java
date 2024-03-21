package org.englising.com.englisingbe.multiplay.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.management.Query;
import org.englising.com.englisingbe.multiplay.dto.request.MultiPlayRequestDto;
import org.englising.com.englisingbe.multiplay.dto.response.MultiPlayListResponseDto;
import org.englising.com.englisingbe.multiplay.entity.MultiPlay;
import org.englising.com.englisingbe.multiplay.repository.MultiPlayImgRepository;
import org.englising.com.englisingbe.multiplay.repository.MultiPlayRepository;
import org.englising.com.englisingbe.music.entity.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MultiPlayServiceImpl implements MultiPlayService {
    private final MultiPlayRepository multiPlayRepository;
    private final MultiPlayImgRepository multiPlayImgRepository;

    @Autowired
    public MultiPlayServiceImpl(MultiPlayRepository multiPlayRepository, MultiPlayImgRepository multiPlayImgRepository) {
        this.multiPlayRepository = multiPlayRepository;
        this.multiPlayImgRepository = multiPlayImgRepository;
    }

    @Override
    public MultiPlay createMultiPlay(MultiPlayRequestDto requestDto) {
        MultiPlay multiPlay = new MultiPlay();
        multiPlay.setGenre(requestDto.getGenre());
        multiPlay.setRoomName(requestDto.getRoomName());
        multiPlay.setIsSecret(requestDto.isSecret);
        multiPlay.setRoomPw(requestDto.roomPw);
        multiPlay.setMaxUser(requestDto.maxUser);
        // 랜덤한 trackId 생성
        Random random = new Random();
        long randomTrackId = random.nextInt(1000); // 예를 들어 1000 이내의 랜덤한 값 생성
        Track track = new Track();
        multiPlay.setTrackId(Track.builder().trackId(randomTrackId).build());
        // 랜덤한 multiPlayImgUrl 생성
        String randomImageUrl = multiPlayImgRepository.getRandomImageUrl();
        multiPlay.setMultiPlayImgUrl(randomImageUrl);

        return multiPlayRepository.save(multiPlay);
    }

    @Override
    public MultiPlay getMultiPlayById(Long multiPlayId) {
        return multiPlayRepository.findByMultiplayId(multiPlayId);
    }

    @Override
    public List<MultiPlayListResponseDto> getMultiPlayList(String genre, Integer page, Integer size) {
        List<MultiPlay> multiPlays = multiPlayRepository.findByGenre(genre, PageRequest.of(page, size));
        List<MultiPlayListResponseDto> multiPlayListResponseDto = new ArrayList<>();
        for (MultiPlay multiPlay : multiPlays) {
            MultiPlayListResponseDto dto = new MultiPlayListResponseDto();
            dto.setMultiplayId(multiPlay.getMultiplayId());
            dto.setRoomName(multiPlay.getRoomName());
            dto.setMaxUser(multiPlay.getMaxUser());
            dto.setMultiPlayImgUrl(multiPlay.getMultiPlayImgUrl());
            dto.setGenre(multiPlay.getGenre());
            multiPlayListResponseDto.add(dto);
        }
        return multiPlayListResponseDto;
    }

    @Override
    public Boolean getMultiPlayResult(Long multiplayId) {
        return multiPlayRepository.findByMultiplayId(multiplayId).getIsSecret();
    }

    @Override
    public List<Long> getMultiPlayList() {
        return null;
    }
}