package cz.muni.fi.civ.newohybat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;





import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cz.muni.fi.civ.newohybat.game.service.GameService;
import cz.muni.fi.civ.newohybat.game.service.GameServiceImpl;
import cz.muni.fi.civ.newohybat.persistence.facade.dto.ActionDTO;
import cz.muni.fi.civ.newohybat.persistence.facade.dto.AdvanceDTO;
import cz.muni.fi.civ.newohybat.persistence.facade.dto.CityDTO;
import cz.muni.fi.civ.newohybat.persistence.facade.dto.CityImprovementDTO;
import cz.muni.fi.civ.newohybat.persistence.facade.dto.GovernmentDTO;
import cz.muni.fi.civ.newohybat.persistence.facade.dto.PlayerDTO;
import cz.muni.fi.civ.newohybat.persistence.facade.dto.SpecialDTO;
import cz.muni.fi.civ.newohybat.persistence.facade.dto.TerrainDTO;
import cz.muni.fi.civ.newohybat.persistence.facade.dto.TileDTO;
import cz.muni.fi.civ.newohybat.persistence.facade.dto.TileImprovementDTO;
import cz.muni.fi.civ.newohybat.persistence.facade.dto.UnitDTO;
import cz.muni.fi.civ.newohybat.persistence.facade.dto.UnitTypeDTO;
import cz.muni.fi.civ.newohybat.persistence.facade.iface.CivBackend;
 
@Path("/ksession")
public class App {
	@Inject
	CivBackend cb;
	@Inject
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
		
		gs.insert(tile);

		String result = "Restful example : " + msg;
 
		return Response.status(200).entity(result).build();
 
	}
	@GET
	@Path("/start")
	public Response start(){
		gs.startGame();
		return Response.status(200).build();
	}
	@GET
	@Path("/stop")
	public Response stop(){
		gs.stopGame();
		return Response.status(200).build();
	}
	@GET
	@Path("/init")
	public Response init(){
		gs.init();
		return Response.status(200).build();
	}
	@GET
	@Path("/load/{param}")
	public Response load(@PathParam("param") Integer sessionId){
		gs.load(sessionId);
		return Response.status(200).build();
	}
	@POST
	@Path("/action/insert/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertAllActions(Collection<ActionDTO> actions){
		// no-op so far
		return Response.status(200).build();
	}
	@GET
	@Path("/advance/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertCityImprovement(AdvanceDTO advance){
		gs.insert(advance);
		
		return Response.status(200).build();
	}
	@POST
	@Path("/advance/insert/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertAdvances(Collection<AdvanceDTO> advances){
		gs.insertAll(advances);
		return Response.status(200).build();
	}
	@GET
	@Path("/advance/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAdvances(){
		return Response.status(200).entity(gs.getAdvances()).build();
	}
	@GET
	@Path("/advance/collect")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAdvances(Collection<String> idents){		
		return Response.status(200).entity(gs.getAdvances(idents)).build();
	}
	@GET
	@Path("/advance/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAdvance(@PathParam("id") String ident){
		return Response.status(200).entity(gs.getAdvance(ident)).build();
	}
	@GET
	@Path("/city/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertCity(CityDTO city) {
		gs.insert(city);

		return Response.status(200).build();
 
	}
	@POST
	@Path("/city/insert/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertAllCities(Collection<CityDTO> cities){
		gs.insertAll(cities);
		return Response.status(200).build();
	}
	@GET
	@Path("/city/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCities() {
		return Response.status(200).entity(gs.getCities()).build();
 
	}
	@GET
	@Path("/city/collect")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getCities(Collection<Long> ids) {
		return Response.status(200).entity(gs.getCities(ids)).build();
 
	}
	@GET
	@Path("/city/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCity(@PathParam("id") Long id) {
		return Response.status(200).entity(gs.getCity(id)).build();
	}
	@POST
	@Path("/city/{id}/recruit/{ident}")
	public Response cityBeginUnit(@PathParam("id") Long id,@PathParam("ident") String ident){
		gs.cityBeginUnit(id, ident);
		return Response.status(200).build();
	}
	@POST
	@Path("/city/{id}/cancel/unit")
	public Response cancelUnit(@PathParam("id") Long id){
		gs.cityStopUnit(id);
		return Response.status(200).build();
	}
	@POST
	@Path("/city/{id}/build/{ident}")
	public Response cityBeginImprovement(@PathParam("id") Long id,@PathParam("ident") String ident){
		gs.cityBeginCityImprovement(id, ident);
		return Response.status(200).build();
	}
	@POST
	@Path("/city/{id}/cancel/cityimprovement")
	public Response cancelImp(@PathParam("id") Long id){
		gs.cityStopCityImprovement(id);
		return Response.status(200).build();
	}
	@GET
	@Path("/cityimprovement/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertCityImprovement(CityImprovementDTO cityImprovement){
		gs.insert(cityImprovement);
		
		return Response.status(200).build();
	}
	@POST
	@Path("/cityimprovement/insert/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertAllCityImprovements(Collection<CityImprovementDTO> cityImprovements){
		gs.insertAll(cityImprovements);
		return Response.status(200).build();
	}
	@GET
	@Path("/cityimprovement/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCityImprovements(){
		return Response.status(200).entity(gs.getCityImprovements()).build();
	}
	@GET
	@Path("/cityimprovement/collect")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getCityImprovements(Collection<String> idents){		
		return Response.status(200).entity(gs.getCityImprovements(idents)).build();
	}
	@GET
	@Path("/cityimprovement/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCityImprovement(@PathParam("id") String ident){
		return Response.status(200).entity(gs.getCityImprovement(ident)).build();
	}
	@POST
	@Path("/government/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertGovernment(GovernmentDTO government){
		gs.insert(government);
		
		return Response.status(200).build();
	}
	@POST
	@Path("/government/insert/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertAllGovernments(Collection<GovernmentDTO> governments){
		gs.insertAll(governments);
		return Response.status(200).build();
	}
	@GET
	@Path("/government/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGovernments(){
		return Response.status(200).entity(gs.getGovernments()).build();
	}
	@GET
	@Path("/government/collect")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getGovernments(Collection<String> idents){		
		return Response.status(200).entity(gs.getGovernments(idents)).build();
	}
	@GET
	@Path("/government/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGovernment(@PathParam("id") String ident){
		return Response.status(200).entity(gs.getGovernment(ident)).build();
	}
	@GET
	@Path("/player/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertPlayer(PlayerDTO player) {
		gs.insert(player);

		return Response.status(200).build();
 
	}
	@POST
	@Path("/player/insert/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertAllPlayers(Collection<PlayerDTO> players){
		gs.insertAll(players);
		return Response.status(200).build();
	}
	@GET
	@Path("/player/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPlayers() {
		return Response.status(200).entity(gs.getPlayers()).build();
 
	}
	@GET
	@Path("/player/collect")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getPlayers(Collection<Long> ids) {
		return Response.status(200).entity(gs.getPlayers(ids)).build();
 
	}
	@GET
	@Path("/player/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPlayer(@PathParam("id") Long id) {
		return Response.status(200).entity(gs.getPlayer(id)).build();
	}
	@POST
	@Path("/player/{id}/government/{ident}")
	public Response changeGovernment(@PathParam("id") Long id,@PathParam("ident") String ident){
		gs.playerChangeGovernment(id, ident);
		return Response.status(200).build();
	}
	@POST
	@Path("/player/{id}/research/{ident}")
	public Response beginResearch(@PathParam("id") Long id,@PathParam("ident") String ident){
		gs.playerBeginAdvance(id, ident);
		return Response.status(200).build();
	}
	@POST
	@Path("/player/{id}/cancel/research")
	public Response cancelResearch(@PathParam("id") Long id){
		gs.playerStopAdvance(id);
		return Response.status(200).build();
	}
	
	@GET
	@Path("/special/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertSpecial(SpecialDTO special){
		gs.insert(special);
		
		return Response.status(200).build();
	}
	@POST
	@Path("/special/insert/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertAllSpecials(Collection<SpecialDTO> specials){
		gs.insertAll(specials);
		
		return Response.status(200).build();
	}
	@GET
	@Path("/special/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSpecials(){
		return Response.status(200).entity(gs.getSpecials()).build();
	}
	@GET
	@Path("/special/collect")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getSpecials(Collection<String> idents){		
		return Response.status(200).entity(gs.getSpecials(idents)).build();
	}
	@GET
	@Path("/special/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSpecial(@PathParam("id") String ident){
		return Response.status(200).entity(gs.getSpecial(ident)).build();
	}
	@GET
	@Path("/terrain/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertTerrain(TerrainDTO terrain){
		gs.insert(terrain);
		return Response.status(200).build();
	}
	@POST
	@Path("/terrain/insert/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertAllTerrains(Collection<TerrainDTO> terrains){
		gs.insertAll(terrains);
		return Response.status(200).build();
	}
	@GET
	@Path("/terrain/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTerrains(){
		return Response.status(200).entity(gs.getTerrains()).build();
	}
	@GET
	@Path("/terrain/collect")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getTerrains(ArrayList<String> idents){
		return Response.status(200).entity(gs.getTerrains(idents)).build();
	}
	@GET
	@Path("/terrain/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTerrain(@PathParam("id") String ident){
		return Response.status(200).entity(gs.getTerrain(ident)).build();
	}
	
	@GET
	@Path("/tile/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertTile(TileDTO tile) {
		gs.insert(tile);

		return Response.status(200).build();
 
	}
	@POST
	@Path("/tile/insert/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertAllTiles(Collection<TileDTO> tiles) {
		gs.insertAll(tiles);

		return Response.status(200).build();
 
	}
	@GET
	@Path("/tile/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTiles() {
		return Response.status(200).entity(gs.getTiles()).build();
 
	}
	@GET
	@Path("/tile/collect")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getTiles(Collection<Long> ids) {
		return Response.status(200).entity(gs.getTiles(ids)).build();
 
	}
	@GET
	@Path("/tile/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTile(@PathParam("id") Long id) {
		return Response.status(200).entity(gs.getTile(id)).build();
	}
	@POST
	@Path("/tileimprovement/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertTileImprovement(TileImprovementDTO tileImprovement){
		gs.insert(tileImprovement);
		return Response.status(200).build();
	}
	@POST
	@Path("/tileimprovement/insert/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertTileImprovement(Collection<TileImprovementDTO> imps){
		gs.insertAll(imps);
		return Response.status(200).build();
	}
	@GET
	@Path("/tileimprovement/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTileImprovements(){
		return Response.status(200).entity(gs.getTileImprovements()).build();
	}
	@GET
	@Path("/tileimprovement/collect")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getTileImprovements(ArrayList<String> idents){
		return Response.status(200).entity(gs.getTileImprovements(idents)).build();
	}
	@GET
	@Path("/tileimprovement/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTileImprovement(@PathParam("id") String ident){
		return Response.status(200).entity(gs.getTileImprovement(ident)).build();
	}
 

	@GET
	@Path("/unit/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertUnit(UnitDTO tile) {
		gs.insert(tile);

		return Response.status(200).build();
 
	}
	@POST
	@Path("/unit/insert/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertUnits(Collection<UnitDTO> tiles) {
		gs.insertAll(tiles);

		return Response.status(200).build();
 
	}
	@GET
	@Path("/unit/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUnits() {
		return Response.status(200).entity(gs.getUnits()).build();
 
	}
	@GET
	@Path("/unit/collect")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getUnits(Collection<Long> ids) {
		return Response.status(200).entity(gs.getUnits(ids)).build();
 
	}
	@GET
	@Path("/unit/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUnit(@PathParam("id") Long id) {
		return Response.status(200).entity(gs.getUnit(id)).build();
	}
	@POST
	@Path("/unit/{id}/begin/{ident}")
	public Response beginAction(@PathParam("id")Long id,@PathParam("ident") String ident) {
		gs.unitBeginAction(id, ident);
		return Response.status(200).build();
	}
	@POST
	@Path("/unit/{id}/cancel/action")
	public Response cancelAction(@PathParam("id") Long id){
		gs.unitStopAction(id);
		gs.unitStopMove(id);
		return Response.status(200).build();
	}
	@POST
	@Path("/unit/{id}/begin/{ident}/{targetTile}")
	public Response beginActionWithParam(@PathParam("id")Long id,@PathParam("ident")String actionIdent,@PathParam("targetTile") Long targetTile) {
		gs.unitBeginMove(id, actionIdent, targetTile);
		return Response.status(200).build();
	}
	@POST
	@Path("/unit/dead/{id}")
	public Response removeDeadUnit(@PathParam("id")Long id) {
		UnitDTO unit = gs.getUnit(id);
		gs.removeDeadUnit(id);
		cb.delete(unit);
		return Response.status(200).build();
	}
	@POST
	@Path("/unittype/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertUnitType(UnitTypeDTO unitType){
		gs.insert(unitType);
		return Response.status(200).build();
	}
	@POST
	@Path("/unittype/insert/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertUnitTypes(Collection<UnitTypeDTO> unitTypes){
		gs.insertAll(unitTypes);
		return Response.status(200).build();
	}
	@GET
	@Path("/unittype/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUnitTypes(){
		return Response.status(200).entity(gs.getUnitTypes()).build();
	}
	@GET
	@Path("/unittype/collect")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getUnitTypes(ArrayList<String> idents){
		return Response.status(200).entity(gs.getUnitTypes(idents)).build();
	}
	@GET
	@Path("/unittype/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUnitType(@PathParam("id") String ident){
		return Response.status(200).entity(gs.getUnitType(ident)).build();
	}
	@POST
	@Path("/tileimprovement/insert/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertTileImprovements(Collection<TileImprovementDTO> imps){
		// no-op so far
		return Response.status(200).build();
	}

 
}
