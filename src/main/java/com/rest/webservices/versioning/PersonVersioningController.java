package com.rest.webservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class PersonVersioningController {

	
	@GetMapping("/v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Vikas Bhardwaj");
	}
	@GetMapping("/v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Chodu","Mal"));
	}
	
	@GetMapping(value="/person/param",params="version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Vikas Bhardwaj");
	}
	@GetMapping(value="/person/param",params="version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Chodu","Mal"));
	}
	
	@GetMapping(value="/person/header",headers="X-API-version=1")
	public PersonV1 headerV1() {
		return new PersonV1("Vikas Bhardwaj");
	}
	@GetMapping(value="/person/header",headers="X-API-version=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Chodu","Mal"));
	}
	@GetMapping(value="/person/producer",produces="application/vnd.company.app-v1+json")
	public PersonV1 producerV1() {
		return new PersonV1("Vikas Bhardwaj");
	}
	@GetMapping(value="/person/producer",produces="application/vnd.company.app-v2+json")
	public PersonV2 producerV2() {
		return new PersonV2(new Name("Chodu","Mal"));
	}
}