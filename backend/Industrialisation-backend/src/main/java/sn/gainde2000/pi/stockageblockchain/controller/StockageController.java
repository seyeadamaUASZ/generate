package sn.gainde2000.pi.stockageblockchain.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.Utilisateur;
import sn.gainde2000.pi.core.service.IUtilisateur;
import sn.gainde2000.pi.stockageblockchain.entity.StockageBlockchain;
import sn.gainde2000.pi.stockageblockchain.service.IStockageService;

@RestController
@CrossOrigin("*")
@RequestMapping("stockageblockchain")
public class StockageController {
	@Autowired
	IStockageService iStockageService;
	@Autowired
	IUtilisateur iUtilisateur;
	
	
	/**
	 *  service de recuperation des documents enrigistres sur la blockchaine par utilisateur
	 *  @return ServeurResponse
	 */
	
	@GetMapping("list/{username}")
	public ServeurResponse getList(@PathVariable("username") String username) {
		System.out.println("--------------- "+ username);
		ServeurResponse response = new ServeurResponse();
		Iterable<StockageBlockchain> liste = iStockageService.getListDocUtilisateur(username);
		
		
		response.setStatut(true);
		response.setData(liste);
		response.setDescription("liste");
		return response;
	}
	
	/**
	 *  service de suppression dans bd
	 */
	
	@GetMapping("delete/{hash}")
	public ServeurResponse supprimerOnBd(@PathVariable String hash) {
		ServeurResponse  response = new ServeurResponse();
		
		iStockageService.deleteWithHash(hash);
		
		return response;
		
	}

	/**
	 * service d'enregistrement du hash dans la blockchain
	 * 
	 * @return ServeurResponse
	 */
	@PostMapping("add")
	public ServeurResponse enregistrerBlockchain(HttpServletRequest request, @RequestParam("file") MultipartFile file,
			@RequestParam("username") String username) {
		ServeurResponse response = new ServeurResponse();
		Utilisateur utilisateur = iUtilisateur.findByUtiUsername(username);
		StockageBlockchain stockageBlockchain;

		try {
			stockageBlockchain = new ObjectMapper().readValue(request.getParameter("document"),
					new TypeReference<StockageBlockchain>() {
					});
			stockageBlockchain.setStblBlob(file.getBytes());
			stockageBlockchain.setStblNom(file.getOriginalFilename());
			stockageBlockchain.setStblType(file.getContentType());
			stockageBlockchain.setStblDate(new Date());
			stockageBlockchain.setUtilisateur(utilisateur);
			StockageBlockchain st = iStockageService.findByStblHash(stockageBlockchain.getStblHash());
			if (st == null) {
				stockageBlockchain = iStockageService.sauvegarderBlockchain(stockageBlockchain);
			} else {
				response.setStatut(false);
				response.setDescription("blockchain.fileAlreadySaved");
				return response;
			}
			if (stockageBlockchain == null) {
				response.setStatut(false);
				response.setDescription("blockchain.hashuniqueerror");
				return response;
			} else {
				response.setStatut(true);
				response.setDescription("blockchain.saveOk");
				response.setData(stockageBlockchain);
				return response;
			}

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
