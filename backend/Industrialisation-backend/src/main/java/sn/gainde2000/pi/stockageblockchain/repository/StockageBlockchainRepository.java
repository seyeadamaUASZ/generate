package sn.gainde2000.pi.stockageblockchain.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sn.gainde2000.pi.core.entity.Utilisateur;
import sn.gainde2000.pi.ged.entity.Document;
import sn.gainde2000.pi.stockageblockchain.entity.StockageBlockchain;

@Repository
public interface StockageBlockchainRepository extends JpaRepository<StockageBlockchain, Long> {
	
	public StockageBlockchain findByStblHash(String stblHash);
	
	@Query("select s from StockageBlockchain s where s.utilisateur.utiUsername = :username")
	public Iterable<StockageBlockchain> getListDocUtilisateur(@Param("username")String username);
	
	@Transactional
	@Modifying
	@Query("Delete from StockageBlockchain s  where s.stblHash = :hash")
	public void deleteWithHash(@Param("hash") String hash);
	
}
