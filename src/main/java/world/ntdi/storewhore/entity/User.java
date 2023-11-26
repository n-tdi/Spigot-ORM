package world.ntdi.storewhore.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DatabaseTable(tableName = "users")
public class User {
    @DatabaseField(id = true)
    private UUID uuid;
    @DatabaseField
    private int age;
}
