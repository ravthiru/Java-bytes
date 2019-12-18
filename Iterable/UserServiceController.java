/*
 * JBoss, Home of Professional Open Source
 * Copyright 2016, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.keycloak.quickstart.springboot.web;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.IDToken;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@RestController
public class UserServiceController {

    private @Autowired HttpServletRequest request;

	@GetMapping(value = "/userinfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserData handleUserInfoRequest(Principal principal) {
		System.out.println("principal "+principal);

		UserData user = new UserData();

		if (principal instanceof KeycloakPrincipal) {

			KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
			AccessToken token = kp.getKeycloakSecurityContext().getToken();
			user.setId(token.getNickName());
			user.setUserName(token.getName());
			Map<String, Object> otherClaims = token.getOtherClaims();
			user.setCustomAttributes(otherClaims);
		}
		return user;
	}

	@GetMapping(value = "/public", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> handlePublicRequest() {
		return Collections.singletonMap("message", "public access");
	}
}
