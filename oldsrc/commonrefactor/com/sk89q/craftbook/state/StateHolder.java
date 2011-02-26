// $Id$
/*
 * CraftBook
 * Copyright (C) 2010 Lymia <lymiahugs@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package com.sk89q.craftbook.state;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.sk89q.craftbook.access.WorldInterface;

public interface StateHolder {
    void resetCommonData();
    void writeCommonData(DataOutput o) throws IOException;
    void readCommonData(DataInput i) throws IOException;
    
    void resetWorldData(WorldInterface w);
    void writeWorldData(WorldInterface w, DataOutput o) throws IOException;
    void readWorldData(WorldInterface w, DataInput i) throws IOException;
}