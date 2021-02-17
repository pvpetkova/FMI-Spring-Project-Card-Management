package bg.fmi.cms.repo;

import bg.fmi.cms.model.Card;
import bg.fmi.cms.model.constats.CardStatus;
import bg.fmi.cms.model.constats.RequestStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
    Optional<Card> getByPan(String pan);

    @Query("SELECT c FROM Card c WHERE (:binId is null or c.bin.id = :binId) and (:cardStatus is null"
            + " or c.cardStatus = :cardStatus)")
    List<Card> findAllByBinIdAndCardStatus(@Param("binId") Long binId, @Param("cardStatus") CardStatus cardStatus);
}
