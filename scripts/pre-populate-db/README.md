## Loteria database import

Default card configuration are shipped with application in a SQLite database:
1. Update data files in `_data` directory.
2. Run `npm install` if needed.
3. Run the following command to import the data from file to database:
```
  npx gulp initData --dataDir=[path_to_source_files]
```
This command will:
1. Create the latest schema from Room definition.
2. Import data to SQLite database.
3. Put it to `app/assets/databases` folder.
