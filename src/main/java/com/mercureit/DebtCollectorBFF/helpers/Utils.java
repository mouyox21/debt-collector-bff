package com.mercureit.DebtCollectorBFF.helpers;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;

public class Utils {

	public static String getHashedUuid(LocalDateTime dateCreation, Long id) {
		UUID uuid = UUID.randomUUID();
		String hash = uuid.toString() + dateCreation + id;
		return DigestUtils.sha256Hex(hash);

	}

}
