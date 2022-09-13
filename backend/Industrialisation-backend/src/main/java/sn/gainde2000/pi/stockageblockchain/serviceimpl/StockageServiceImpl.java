package sn.gainde2000.pi.stockageblockchain.serviceimpl;

import javax.persistence.RollbackException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sn.gainde2000.pi.core.entity.Utilisateur;
import sn.gainde2000.pi.stockageblockchain.entity.StockageBlockchain;
import sn.gainde2000.pi.stockageblockchain.repository.StockageBlockchainRepository;
import sn.gainde2000.pi.stockageblockchain.service.IStockageService;


@Service
public class StockageServiceImpl implements IStockageService {

	@Autowired StockageBlockchainRepository stockageBlockchainRepository;
	
	@Override
	public StockageBlockchain sauvegarderBlockchain(StockageBlockchain stockageBlockchain) {
		StockageBlockchain st = null;
		try {
			st = stockageBlockchainRepository.save(stockageBlockchain);
		}catch(RollbackException ex) {
			ex.printStackTrace();
			st = null;
		}
		return st;
	}

	@Override
	public StockageBlockchain findByStblHash(String stblHash) {
		// TODO Auto-generated method stub
		return stockageBlockchainRepository.findByStblHash(stblHash);
	}

	@Override
	public Iterable<StockageBlockchain> getListDocUtilisateur(String username) {
		return stockageBlockchainRepository.getListDocUtilisateur(username);
	}

	@Override
	public void deleteWithHash(String hash) {
		stockageBlockchainRepository.deleteWithHash(hash);
		
	}

	

}
