/*
 *  ******************************************************************************
 *  *
 *  *
 *  * This program and the accompanying materials are made available under the
 *  * terms of the Apache License, Version 2.0 which is available at
 *  * https://www.apache.org/licenses/LICENSE-2.0.
 *  *
 *  *  See the NOTICE file distributed with this work for additional
 *  *  information regarding copyright ownership.
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  * License for the specific language governing permissions and limitations
 *  * under the License.
 *  *
 *  * SPDX-License-Identifier: Apache-2.0
 *  *****************************************************************************
 */

package org.datavec.api.transform.reduce;

import org.datavec.api.transform.ColumnOp;
import org.datavec.api.transform.metadata.ColumnMetaData;
import org.datavec.api.writable.Writable;

import java.io.Serializable;
import java.util.List;

public interface ColumnReduction extends Serializable, ColumnOp {

    /**
     * Reduce a single column.
     * <b>Note</b>: The {@code List<Writable>}
     * here is a single <b>column</b> in a reduction window,
     * and NOT the single row
     * (as is usually the case for {@code List<Writable>} instances
     *
     * @param columnData The Writable objects for a column
     * @return Writable containing the reduced data
     */
    Writable reduceColumn(List<Writable> columnData);

    /**
     * Post-reduce: what is the name of the column?
     * For example, "myColumn" -> "mean(myColumn)"
     *
     * @param columnInputName Name of the column before reduction
     * @return Name of the column after the reduction
     */
    String getColumnOutputName(String columnInputName);

    /**
     * Post-reduce: what is the metadata (type, etc) for this column?
     * For example: a "count unique" operation on a String (StringMetaData) column would return an Integer (IntegerMetaData) column
     *
     * @param columnInputMeta Metadata for the column, before reduce
     * @return Metadata for the column, after the reduction
     */
    ColumnMetaData getColumnOutputMetaData(String newColumnName, ColumnMetaData columnInputMeta);

}
