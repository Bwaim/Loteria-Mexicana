import {Database, Statement} from "sqlite3";
import {Card} from "./data/Card";

export async function populate(db: Database, cards: Array<Card>): Promise<void> {
    console.log("Populating Card database...");
    await populateCardSet(db, cards);
}

function isValidData(card: Card): Boolean {
    return card.name.length > 0;
}

async function populateCardSet(db: Database, cards: Array<Card>) {
    console.log("Populating Card table...");
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
            "REPLACE INTO card VALUES (?,?)",
            function(this: Statement, err: Error) {
                if(null != err) {
                    reject(err);
                } else {
                    resolve(this);
                }
            }
        );
    });

    for (const card of cards) {
        if (false == isValidData(card)) {
            console.warn(`Invalid card: ${card.name}`);
            invalidRecords.push(card.name);
            continue;
        }
        await new Promise<void>((resolve, reject) => {
            stmt.run(
                [
                    card.id,
                    card.name
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
    console.log("Cards populated.");
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
