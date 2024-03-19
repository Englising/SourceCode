package org.englising.com.englisingbe.multiplay.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.englising.com.englisingbe.singleplay.entity.SinglePlayHint;
import org.englising.com.englisingbe.track.entity.Track;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Data
@Builder
@Table(name = "multiplay")
@NoArgsConstructor
@AllArgsConstructor
public class MultiPlay {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "multiplay_id")
    private Long multiplayId;

    @Column(name = "room_name")
    private String roomName;

    @ManyToOne
    @JoinColumn(name = "track_id", referencedColumnName = "track_id")
    private Track trackId;

    @Column(name = "total_people")
    private Integer totalPeople;

    @Column(name = "genre")
    private String genre;

    @Column(name = "is_secret")
    private Boolean isSecret;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
