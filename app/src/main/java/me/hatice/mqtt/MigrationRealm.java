package me.hatice.mqtt;

import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class MigrationRealm implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        Log.d("realm", "old version:" + oldVersion + ",new version:" + newVersion );
        // DynamicRealm exposes an editable schema
        RealmSchema schema = realm.getSchema();

        if( oldVersion == 0 ) {
            schema.create("Device")
                    .addField("name", String.class);
        }


            /*

            // Migrate to version 1: Add a new class.
            // Example:
            // public Person extends RealmObject {
            //     private String name;
            //     private int age;
            //     // getters and setters left out for brevity
            // }
            if (oldVersion == 0) {
                schema.create("Person")
                        .addField("name", String.class)
                        .addField("age", int.class);
                oldVersion++;
            }

            // Migrate to version 2: Add a primary key + object references
            // Example:
            // public Person extends RealmObject {
            //     private String name;
            //     @PrimaryKey
            //     private int age;
            //     private Dog favoriteDog;
            //     private RealmList<Dog> dogs;
            //     // getters and setters left out for brevity
            // }
            if (oldVersion == 1) {
                schema.get("Person")
                        .addRealmObjectField("favoriteDog", schema.get("Dog"))
                        .addRealmListField("dogs", schema.get("Dog"));
                oldVersion++;
            }
            */
    }
}
