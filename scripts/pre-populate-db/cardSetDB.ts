import {Database, Statement} from "sqlite3";
import {CardSet} from "./data/CardSet";

export async function populate(db: Database, cardSets: Array<CardSet>): Promise<void> {
    console.log("Populating CardSet database...");
    await populateCardSet(db, cardSets);
}

function isValidData(cardSet: CardSet): Boolean {
    return cardSet.name.length > 0;
}

async function populateCardSet(db: Database, cardSets: Array<CardSet>) {
    console.log("Populating CardSet table...");
    let validRecords = 0;
    let invalidRecords: Array<string> = [];

    db.serialize();
    await new Promise<void> (((resolve, reject) => {
        db.run(
            "begin transaction",
            function(err: Error) {
                if(null != err) {
                    reject(err);
                } else {
                    resolve();
                }
            }
        )
    }));
    const stmt = await new Promise<Statement> ((resolve, reject) => {
        db.prepare(
            "REPLACE INTO card_set VALUES (?,?)",
            function(this: Statement, err: Error) {
                if(null != err) {
                    reject(err);
                } else {
                    resolve(this);
                }
            }
        );
    });

    for (const cardSet of cardSets) {
        if (false == isValidData(cardSet)) {
            console.warn(`Invalid cardSet: ${cardSet.name}`);
            invalidRecords.push(cardSet.name);
            continue;
        }
        await new Promise<void>((resolve, reject) => {
            stmt.run(
                [
                    cardSet.id,
                    cardSet.name
                ],
                function (err: Error) {
                    if (null != err) {
                        reject(err);
                    } else {
                        validRecords++;
                        resolve();
                    }
                }
            );
        })
    }
    console.log("CardSets populated.");
    console.log(`Valid records: ${validRecords}`);
    console.log(`Invalid records (${invalidRecords.length}):`, invalidRecords);
    await new Promise<void> (((resolve, reject) => {
        stmt.finalize(
            function(err: Error) {
                if(null != err) {
                    reject(err);
                } else {
                    resolve();
                }
            }
        )
    }));
    await new Promise<void> (((resolve, reject) => {
        db.run(
            "commit",
            function(err: Error) {
                if(null != err) {
                    reject(err);
                } else {
                    resolve();
                }
            }
        )
    }));
}
