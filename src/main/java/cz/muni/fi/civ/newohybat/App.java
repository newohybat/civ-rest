package cz.muni.fi.civ.newohybat;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cz.muni.fi.civ.newohybat.game.service.GameService;
import cz.muni.fi.civ.newohybat.persistence.facade.dto.TileDTO;
import cz.muni.fi.civ.newohybat.persistence.facade.iface.CivBackend;
 
@Path("/")
public class App {
	@Inject
	CivBackend cb;
	@Inject @ApplicationScoped
	GameService gs;
	@Inject
	UserTransaction utx;
	@GET
	@Path("/insert/{param}/{bla}")
	public Response printMessage(@PathParam("param") String msg,@PathParam("bla")String bla) {
 
		TileDTO tile = new TileDTO();
		tile.setId(new Long(bla));
		tile.setPosX(new Long(msg));
		tile.setPosY(new Long(msg));
		tile.setFoodProduction(0);tile.setResourcesProduction(0);tile.setTradeProduction(0);
		tile.setTerrain("swamp");
		tile.setSpecial("oil");
		
		try {
			utx.begin();
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gs.insert(tile);
		try {
			utx.commit();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = "Restful example : " + msg;
 
		return Response.status(200).entity(result).build();
 
	}
	@GET
	@Path("/get/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response houMessage(@PathParam("param") String msg) {
		
	
		String result = cb.getPlayers().toString();
		try {
			utx.begin();
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<TileDTO> tDtos = gs.get(new Long(msg));
		String s = ((TileDTO)tDtos.get(0)).getId().toString();
		try {
			utx.commit();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity(tDtos.get(0)).build();
 
	}
 
}
