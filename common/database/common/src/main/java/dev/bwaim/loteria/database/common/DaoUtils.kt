/*
 * Copyright 2022 Dev Bwaim team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.bwaim.loteria.database.common

public suspend fun <T> upsert(
    items: List<T>,
    insertMany: suspend (List<T>) -> List<Long>,
    updateMany: suspend (List<T>) -> Unit,
) {
    val insertResults = insertMany(items)

    val updateList = items.zip(insertResults)
        .mapNotNull { (item, insertResult) ->
            if (insertResult == -1L) item else null
        }
    if (updateList.isNotEmpty()) updateMany(updateList)
}
