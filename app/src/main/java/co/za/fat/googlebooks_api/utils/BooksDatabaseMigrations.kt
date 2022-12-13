package co.za.fat.googlebooks_api.utils

import androidx.room.RenameColumn
import androidx.room.migration.AutoMigrationSpec

@RenameColumn(
    tableName = "books",
    fromColumnName = "imageLink",
    toColumnName = "imageLinks"
)
class BooksDatabaseMigrations : AutoMigrationSpec {
}