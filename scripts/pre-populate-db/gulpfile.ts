import minimist, {ParsedArgs} from "minimist";
import {initData} from ".";
import * as path from "path"
import * as fs from "fs"

/**
 * DB schema path
 */
const SCHEMA_PATH = path.join(__dirname, "..", "..", "common", "database", "database", "schemas", "dev.bwaim.loteria.database.LoteriaDatabase");
/**
 * DB destination file
 */
const DST_PATH = path.join(__dirname, "..", "..", "common", "database", "database", "src", "main", "assets", "database", "loteria.db");
/**
 * DB version file
 */
// const VERSION_PATH = path.join(__dirname, "..", "android", "app", "gradle.properties");
/**
 * DB version key
 */
// const VERSION_KEY = "CITIES_DB_VERSION";

/**
 * Known arguments
 */
interface Args extends ParsedArgs {
    dataDir: string
}

/**
 * Increments database version which room uses to guess if new data is available
 * @param file Path to properties
 * @param key Key to set version to
 */
// function incrementDatabaseVersion(file: string, key: string) {
//     const versionRegex = new RegExp(`${key}\\s*=\\s*(\\d+)`, "gi");
//     let contents = fs.readFileSync(file, "utf8");
//     const matches = versionRegex.exec(contents);
//     if (null === matches) {
//         throw new Error("Did not find match in specified file");
//     }
//
//     const version = parseInt(matches[1]);
//     const newVersion = version + 1;
//
//     console.log("Current version: " + version + ". New version: " + newVersion);
//
//     contents = contents.replace(versionRegex, `${key}=${newVersion}`);
//
//     fs.writeFileSync(file, contents)
// }

/**
 * City population
 */
exports.initData = async () => {
    const options: Args = minimist<Args>(
        process.argv.slice(2),
        {
            string: ["dataDir"],
            default: { dataDir: "_data"}
        }
    );

    // 1. Create database
    const pathToNewDb = await initData(SCHEMA_PATH, options.dataDir);
    // 2. Copy to assets
    fs.copyFileSync(pathToNewDb, DST_PATH);
    // 3. Increment version
//     incrementDatabaseVersion(VERSION_PATH, VERSION_KEY);
};
