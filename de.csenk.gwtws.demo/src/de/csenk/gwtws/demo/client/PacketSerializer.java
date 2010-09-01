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

package de.csenk.gwtws.demo.client;

import de.csenk.gwtws.demo.shared.Packet;
import de.csenk.gwtws.shared.filter.serialization.GWTSerializer;
import de.csenk.gwtws.shared.filter.serialization.Serializable;

/**
 * @author Christian
 * @date 01.09.2010
 * @time 22:33:00
 *
 */
@Serializable({Packet.class})
public interface PacketSerializer extends GWTSerializer {

}