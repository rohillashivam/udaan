package com.udaan.parkinglot.aggregate.response;

public interface ResponseCreator<Request, Response> {

	Response createResponse(Request req);
}
