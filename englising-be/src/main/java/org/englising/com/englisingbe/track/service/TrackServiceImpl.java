package org.englising.com.englisingbe.track.service;

import lombok.RequiredArgsConstructor;
import org.englising.com.englisingbe.global.exception.ErrorHttpStatus;
import org.englising.com.englisingbe.global.exception.GlobalException;
import org.englising.com.englisingbe.track.dto.TrackAlbumArtistDto;
import org.englising.com.englisingbe.track.repository.TrackLikeRepositorySupport;
import org.englising.com.englisingbe.track.repository.TrackRepository;
import org.englising.com.englisingbe.track.repository.TrackRepositorySupport;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrackServiceImpl {
    private final TrackRepository trackRepository;
    private final TrackRepositorySupport trackRepositorySupport;

    public List<TrackAlbumArtistDto> getTracksByTrackIds(List<Long> trackIds) {
        return trackIds.stream()
                .map(trackId -> trackRepositorySupport.findTrackWithAlbumAndArtistsByTrackId(trackId)
                        .orElseThrow(() -> new GlobalException(ErrorHttpStatus.NO_MATCHING_TRACK)))
                .collect(Collectors.toList());
    }

    public TrackAlbumArtistDto getTrackByTrackId(Long trackId) {
        return trackRepositorySupport.findTrackWithAlbumAndArtistsByTrackId(trackId)
                .orElseThrow(() -> new GlobalException(ErrorHttpStatus.NO_MATCHING_TRACK));
    }
}
