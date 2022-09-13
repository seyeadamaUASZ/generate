package sn.gainde2000.pi.stockageblockchain.service;

import sn.gainde2000.pi.core.entity.Utilisateur;
import sn.gainde2000.pi.stockageblockchain.entity.StockageBlockchain;

public interface IStockageService {
	
	public StockageBlockchain sauvegarderBlockchain(StockageBlockchain stockageBlockchain);
	public StockageBlockchain findByStblHash(String stblHash);
	public Iterable<StockageBlockchain> getListDocUtilisateur(String username);
	public void deleteWithHash(String hash);

}
