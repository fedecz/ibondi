package com.uade.pfi.web.controller;

import org.springframework.web.bind.annotation.RequestBody;

import com.uade.pfi.core.dto.TransportLocation;

public interface MobileInterface {

	public abstract Integer count();

	public abstract TransportLocation[] list();

	public abstract Boolean postLocation(@RequestBody TransportLocation location);

}