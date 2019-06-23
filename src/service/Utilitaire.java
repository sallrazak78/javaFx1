package service;

import java.util.List;
import java.util.stream.Collectors;

import model.Location;
import model.LocationModel;

public class Utilitaire {
	public static List<LocationModel> connvertToLocationModele(List<Location> location){
		return location.stream().map(l->new LocationModel(l)).collect(Collectors.toList());
	}
}
