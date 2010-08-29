/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.csenk.gwtws.server.filter.serialization;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.impl.ServerSerializationStreamReader;

import de.csenk.gwtws.shared.filter.serialization.GWTSerializer;

/**
 * @author Christian
 * @date 28.08.2010
 * @time 19:13:33
 * 
 */
public class ServerGWTSerializer implements GWTSerializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.csenk.gwtws.shared.filter.serialization.GWTSerializer#deserialize(
	 * java.lang.String)
	 */
	@Override
	public Object deserialize(String serializedContent)
			throws SerializationException {

		System.out.println(Thread.currentThread().getContextClassLoader().toString());
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		ServerSerializationStreamReader streamReader = new ServerSerializationStreamReader(
				classLoader, null);
		streamReader.prepareToRead(serializedContent);

		String objClassName = maybeDeobfuscate(streamReader, streamReader
				.readString());

		try {
			Class<?> objClass = Class.forName(objClassName, false, classLoader);

//			Class<?> customSerializer = SerializabilityUtil
//					.hasCustomFieldSerializer(objClass);
//			if (customSerializer != null) {
//				for (Method method : customSerializer.getMethods()) {
//					if ("instantiate".equals(method.getName())) {
//						Class<?> paramClass = method.getParameterTypes()[0];
//						Class<?> readerClass = streamReader.getClass();
//						System.out.println(paramClass.getClassLoader().toString());
//						System.out.println(readerClass.getClassLoader().toString());
//						
//						boolean some = paramClass.isAssignableFrom(readerClass);
//						Object instance = method.invoke(null, streamReader);
//					}
//				}
//				// Ok to not have one.
//			}

			 return streamReader.deserializeValue(objClass);
//			return new Object();
		} catch (ClassNotFoundException e) {
			throw new SerializationException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.csenk.gwtws.shared.filter.serialization.GWTSerializer#serialize(java
	 * .lang.Object)
	 */
	@Override
	public String serialize(Object obj) throws SerializationException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Given a type identifier in the stream, attempt to deobfuscate it. Retuns
	 * the original identifier if deobfuscation is unnecessary or no mapping is
	 * known.
	 */
	private static String maybeDeobfuscate(
			ServerSerializationStreamReader streamReader, String name)
			throws SerializationException {
		int index;
		if ((index = name.indexOf('/')) != -1) {
			return name.substring(0, index);
		}
		return name;
	}
}
