package ca.qc.plachanc73.restws.common.config;

public final class ThreadUserManager {

	private static final String KEY_CLIENT_ID = "-uid=";

	private static final String DEFAULT_CLIENT_ID = "SYSTEM";

	private ThreadUserManager() {
		// Aucune initialisation
	}

	public static void setClientId(String clientId) {
		if (hasClientId()) {
			removeClientId();
		}

		Thread.currentThread().setName(Thread.currentThread().getName() + KEY_CLIENT_ID + clientId);
	}

	/**
	 * Obtient l'identifiant du client.
	 * 
	 * @return String
	 */
	public static String getClientId() {
		String clientId = DEFAULT_CLIENT_ID;
		String threadName = Thread.currentThread().getName();
		int clientIdKeyIndex = threadName.indexOf(KEY_CLIENT_ID);

		if (clientIdKeyIndex > -1) {
			clientId = threadName.substring(clientIdKeyIndex + KEY_CLIENT_ID.length());
		}

		return clientId;
	}

	/**
	 * Détermine si un identifiant d'un client existe.
	 * 
	 * @return Vrai si un ClientId existe
	 */
	public static boolean hasClientId() {
		boolean hasClientId = false;
		String threadName = Thread.currentThread().getName();
		int clientIdKeyIndex = threadName.indexOf(KEY_CLIENT_ID);

		if (clientIdKeyIndex > -1) {
			hasClientId = true;
		}

		return hasClientId;
	}

	/**
	 * Supprime l'identifiant du client s'il y en a un.
	 */
	private static void removeClientId() {
		String threadName = Thread.currentThread().getName();
		int clientIdKeyIndex = threadName.indexOf(KEY_CLIENT_ID);
		Thread.currentThread().setName(threadName.substring(0, clientIdKeyIndex));
	}
}