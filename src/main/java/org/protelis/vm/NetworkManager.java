/*******************************************************************************
 * Copyright (C) 2010, 2015, Danilo Pianini and contributors
 * listed in the project's build.gradle or pom.xml file.
 *
 * This file is part of Protelis, and is distributed under the terms of
 * the GNU General Public License, with a linking exception, as described
 * in the file LICENSE.txt in this project's top directory.
 *******************************************************************************/
package org.protelis.vm;

import java.util.Map;

import org.protelis.lang.datatype.DeviceUID;
import org.protelis.vm.util.CodePath;

/**
 * @author Danilo Pianini
 * Abstraction of networking used by the virtual machine: at each execution round, the VM needs
 * to be able to access the most recent state received from neighbors and to be able to update
 * the state that it is exporting to neighbors.
 * 
 * Note, however, that there is no requirement that state actually be sent or received in each
 * round: it is up to the individual implementation of a NetworkManager to best optimize in order
 * to best trade off between effective state sharing and efficiency.
 */
public interface NetworkManager {

	/**
	 * Called by {@link ProtelisVM} during execution to collect the most recent information available from neighbors.
	 * @return A map associated each neighbor with its shared state
	 */
	Map<DeviceUID, Map<CodePath, Object>> takeMessages();

	/**
	 * Called by {@link ProtelisVM} during execution to send its current shared state to neighbors.
	 * @param toSend 
	 * 		Shared state to be transmitted to neighbors.
	 */
	void sendMessage(Map<CodePath, Object> toSend);

}
