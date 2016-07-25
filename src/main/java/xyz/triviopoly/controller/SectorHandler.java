package xyz.triviopoly.controller;

import xyz.triviopoly.model.Sector;

public interface SectorHandler {

	boolean handles(Sector sector);

	void handle(Sector sector);
}
