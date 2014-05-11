package cz.muni.fi.civ.newohybat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;




import java.util.Set;

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
 
@Path("/db")
public class DBRest {
	@Inject
	CivBackend cb;

	@POST
	@Path("/action/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAction(ActionDTO action){
		return Response.status(200).entity(cb.create(action)).build();
	}
	@POST
	@Path("/action/create/all")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertAllActions(Collection<ActionDTO> actions){
		Set<ActionDTO> created = new HashSet<ActionDTO>();
		for(ActionDTO a:actions){
			created.add(cb.create(a));
		}
		return Response.status(200).entity(created).build();
	}
	@GET
	@Path("/action/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActions(){
		return Response.status(200).entity(cb.getActions()).build();
	}
	@GET
	@Path("/action/get/{ident}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAction(@PathParam("ident") String ident){
		return Response.status(200).entity(cb.getActionByIdent(ident)).build();
	}
	@POST
	@Path("/advance/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAdvance(AdvanceDTO advance){
		return Response.status(200).entity(cb.create(advance)).build();
	}
	@POST
	@Path("/advance/create/all")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertAllAdvances(Collection<AdvanceDTO> advances){
		Set<AdvanceDTO> created = new HashSet<AdvanceDTO>();
		for(AdvanceDTO a:advances){
			created.add(cb.create(a));
		}
		return Response.status(200).entity(created).build();
	}
	@GET
	@Path("/advance/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAdvances(){
		return Response.status(200).entity(cb.getAdvances()).build();
	}
//	@GET
//	@Path("/advance/collect")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response getAdvances(Collection<String> idents){		
//		return Response.status(200).entity(cb.getAdvances(idents)).build();
//	}
	@GET
	@Path("/advance/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAdvance(@PathParam("id") String ident){
		return Response.status(200).entity(cb.getAdvanceByIdent(ident)).build();
	}
	@POST
	@Path("/city/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertCity(CityDTO city) {
		return Response.status(200).entity(cb.create(city)).build();
 
	}
	@POST
	@Path("/city/create/all")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertAllCities(Collection<CityDTO> cities){
		Set<CityDTO> created = new HashSet<CityDTO>();
		for(CityDTO c:cities){
			created.add(cb.create(c));
		}
		return Response.status(200).entity(created).build();
	}
	@GET
	@Path("/city/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCities() {
		return Response.status(200).entity(cb.getCities()).build();
 
	}
//	@GET
//	@Path("/city/collect")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response getCities(Collection<Long> ids) {
//		return Response.status(200).entity(cb.getCities(ids)).build();
// 
//	}
	@GET
	@Path("/city/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCity(@PathParam("id") Long id) {
		return Response.status(200).entity(cb.getCityById(id)).build();
	}
	@POST
	@Path("/cityimprovement/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertCityImprovement(CityImprovementDTO cityImprovement){
		return Response.status(200).entity(cb.create(cityImprovement)).build();
	}
	@POST
	@Path("/cityimprovement/create/all")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertAllCityImprovements(Collection<CityImprovementDTO> cityImprovements){
		Set<CityImprovementDTO> created = new HashSet<CityImprovementDTO>();
		for(CityImprovementDTO imp:cityImprovements){
			created.add(cb.create(imp));
		}
		return Response.status(200).entity(created).build();
	}
	@GET
	@Path("/cityimprovement/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCityImprovements(){
		return Response.status(200).entity(cb.getCityImprovements()).build();
	}
//	@GET
//	@Path("/cityimprovement/collect")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response getCityImprovements(Collection<String> idents){		
//		return Response.status(200).entity(cb.getCityImprovements(idents)).build();
//	}
	@GET
	@Path("/cityimprovement/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCityImprovement(@PathParam("id") String ident){
		return Response.status(200).entity(cb.getCityImprovementByIdent(ident)).build();
	}
	@POST
	@Path("/government/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertGovernment(GovernmentDTO government){
		return Response.status(200).entity(cb.create(government)).build();
	}
	@POST
	@Path("/government/create/all")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertAllGovernments(Collection<GovernmentDTO> governments){
		Set<GovernmentDTO> created = new HashSet<GovernmentDTO>();
		for(GovernmentDTO g:governments){
			created.add(cb.create(g));
		}
		return Response.status(200).entity(created).build();
	}
	@GET
	@Path("/government/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGovernments(){
		return Response.status(200).entity(cb.getGovernments()).build();
	}
//	@GET
//	@Path("/government/collect")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response getGovernments(Collection<String> idents){		
//		return Response.status(200).entity(cb.getGovernments(idents)).build();
//	}
	@GET
	@Path("/government/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGovernment(@PathParam("id") String ident){
		return Response.status(200).entity(cb.getGovernmentByIdent(ident)).build();
	}
	@POST
	@Path("/player/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertPlayer(PlayerDTO player) {
		return Response.status(200).entity(cb.create(player)).build();
 	}
	@POST
	@Path("/player/create/all")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertAllPlayers(Collection<PlayerDTO> players){
		Set<PlayerDTO> created = new HashSet<PlayerDTO>();
		for(PlayerDTO p:players){
			created.add(cb.create(p));
		}
		return Response.status(200).entity(created).build();
	}
	@GET
	@Path("/player/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPlayers() {
		return Response.status(200).entity(cb.getPlayers()).build();
 
	}
//	@GET
//	@Path("/player/collect")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response getPlayers(Collection<Long> ids) {
//		return Response.status(200).entity(cb.getPlayers(ids)).build();
// 
//	}
	@GET
	@Path("/player/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPlayer(@PathParam("id") Long id) {
		return Response.status(200).entity(cb.getPlayerById(id)).build();
	}
	@POST
	@Path("/special/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertSpecial(SpecialDTO special){
		return Response.status(200).entity(cb.create(special)).build();
	}
	@POST
	@Path("/special/create/all")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertAllSpecials(Collection<SpecialDTO> specials){
		Set<SpecialDTO> created = new HashSet<SpecialDTO>();
		for(SpecialDTO s:specials){
			created.add(cb.create(s));
		}
		return Response.status(200).entity(created).build();
	}
	@GET
	@Path("/special/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSpecials(){
		return Response.status(200).entity(cb.getSpecials()).build();
	}
//	@GET
//	@Path("/special/collect")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response getSpecials(Collection<String> idents){		
//		return Response.status(200).entity(cb.getSpecials(idents)).build();
//	}
	@GET
	@Path("/special/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSpecial(@PathParam("id") String ident){
		return Response.status(200).entity(cb.getSpecialByIdent(ident)).build();
	}
	@POST
	@Path("/terrain/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertTerrain(TerrainDTO terrain){
		return Response.status(200).entity(cb.create(terrain)).build();
	}
	@POST
	@Path("/terrain/create/all")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertAllTerrains(Collection<TerrainDTO> terrains){
		Set<TerrainDTO> created = new HashSet<TerrainDTO>();
		for(TerrainDTO t: terrains){
			created.add(cb.create(t));
		}
		return Response.status(200).entity(created).build();
	}
	@GET
	@Path("/terrain/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTerrains(){
		return Response.status(200).entity(cb.getTerrains()).build();
	}
//	@GET
//	@Path("/terrain/collect")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response getTerrains(ArrayList<String> idents){
//		return Response.status(200).entity(cb.getTerrains(idents)).build();
//	}
	@GET
	@Path("/terrain/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTerrain(@PathParam("id") String ident){
		return Response.status(200).entity(cb.getTerrainByIdent(ident)).build();
	}
	
	@POST
	@Path("/tile/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertTile(TileDTO tile) {
		return Response.status(200).entity(cb.create(tile)).build();
 
	}
	@POST
	@Path("/tile/create/all")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertAllTiles(Collection<TileDTO> tiles) {
		Set<TileDTO> created = new HashSet<TileDTO>();
		for(TileDTO t: tiles){
			created.add(cb.create(t));
		}
		return Response.status(200).entity(created).build();
 
	}
	@GET
	@Path("/tile/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTiles() {
		return Response.status(200).entity(cb.getTiles()).build();
 
	}
//	@GET
//	@Path("/tile/collect")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response getTiles(Collection<Long> ids) {
//		return Response.status(200).entity(cb.getTiles(ids)).build();
// 
//	}
	@GET
	@Path("/tile/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTile(@PathParam("id") Long id) {
		return Response.status(200).entity(cb.getTileById(id)).build();
	}
	@POST
	@Path("/tileimprovement/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertTileImprovement(TileImprovementDTO tileImprovement){
		return Response.status(200).entity(cb.create(tileImprovement)).build();
	}
	@POST
	@Path("/tileimprovement/create/all")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertAllTileImprovements(Collection<TileImprovementDTO> imps) {
		Set<TileImprovementDTO> created = new HashSet<TileImprovementDTO>();
		for(TileImprovementDTO t: imps){
			created.add(cb.create(t));
		}
		return Response.status(200).entity(created).build();
 
	}
	@GET
	@Path("/tileimprovement/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTileImprovements(){
		return Response.status(200).entity(cb.getTileImprovements()).build();
	}
//	@GET
//	@Path("/tileimprovement/collect")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response getTileImprovements(ArrayList<String> idents){
//		return Response.status(200).entity(cb.getTileImprovements(idents)).build();
//	}
	@GET
	@Path("/tileimprovement/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTileImprovement(@PathParam("id") String ident){
		return Response.status(200).entity(cb.getTileImprovementByIdent(ident)).build();
	}
 

	@POST
	@Path("/unit/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertUnit(UnitDTO tile) {
		return Response.status(200).entity(cb.create(tile)).build();
	}
	@POST
	@Path("/unit/create/all")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertUnits(Collection<UnitDTO> units) {
		Set<UnitDTO> created = new HashSet<UnitDTO>();
		for(UnitDTO u:units){
			created.add(cb.create(u));
		}
		return Response.status(200).entity(created).build();
	}
	@GET
	@Path("/unit/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUnits() {
		return Response.status(200).entity(cb.getUnits()).build();
 
	}
//	@GET
//	@Path("/unit/collect")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response getUnits(Collection<Long> ids) {
//		return Response.status(200).entity(cb.getUnits(ids)).build();
// 
//	}
	@GET
	@Path("/unit/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUnit(@PathParam("id") Long id) {
		return Response.status(200).entity(cb.getUnitById(id)).build();
	}	
	@POST
	@Path("/unittype/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertUnitType(UnitTypeDTO unitType){
		return Response.status(200).entity(cb.create(unitType)).build();
	}
	@POST
	@Path("/unittype/create/all")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertUnitTypes(Collection<UnitTypeDTO> unitTypes){
		Set<UnitTypeDTO> created = new HashSet<UnitTypeDTO>();
		for(UnitTypeDTO u:unitTypes){
			created.add(cb.create(u));
		}
		return Response.status(200).entity(created).build();
	}
	@GET
	@Path("/unittype/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUnitTypes(){
		return Response.status(200).entity(cb.getUnitTypes()).build();
	}
//	@GET
//	@Path("/unittype/collect")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response getUnitTypes(ArrayList<String> idents){
//		return Response.status(200).entity(cb.getUnitTypes(idents)).build();
//	}
	@GET
	@Path("/unittype/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUnitType(@PathParam("id") String ident){
		return Response.status(200).entity(cb.getUnitTypeByIdent(ident)).build();
	}
 
}
