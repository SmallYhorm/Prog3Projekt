package Recipe;

import database.Database;
import database.RecipeDatabase;
import org.jooq.Record;
import org.jooq.Result;
import javax.swing.table.DefaultTableModel;
import java.util.Map;

/**
 * The Recipes.RecipeModel class handles the logic and data manipulation for managing recipes.
 * It bridges the Recipes.RecipeView (via the controller) and the database layer (RecipeDatabase).
 * This version decouples it from UI components (like JTextField).
 */
public class RecipeModel {
    private RecipeDatabase recipeDatabase;
    private DefaultTableModel tableModel;

    public RecipeModel() {
        try {
            recipeDatabase = new RecipeDatabase(Database.getDslContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.tableModel = null;
    }

    /**
     * Reload all recipes from the database.
     */
    public Result<Record> reloadRecipes() {
        tableModel.setRowCount(0);
        try {
            return recipeDatabase.fetchAllRecipes();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Add a new recipe using the provided data.
     * @param data A map containing the recipe properties. Keys are column names.
     * @return Result containing the inserted recipe.
     */
    public Result<Record> addRecipe(Map<String, String> data) {
        try {
            return recipeDatabase.insertRecipe(
                    Integer.parseInt(data.get("id")),
                    getStringOrNull(data.get("name")),
                    getStringOrNull(data.get("cuisine")),
                    getStringOrNull(data.get("category")),
                    getStringOrNull(data.get("instructions")),
                    parseIntegerOrNull(data.get("nutrition")),
                    getStringOrNull(data.get("cookingTime")),
                    getStringOrNull(data.get("ingredient"))
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Delete a single recipe based on its ID.
     * @param id The recipe ID to delete.
     * @return The result after deletion.
     */
    public Result<Record> deleteRecipe(String id) {
        try {
            int parsedId = Integer.parseInt(id);
            return recipeDatabase.deleteRecipe(parsedId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Delete all recipes from the database.
     */
    public Result<Record> deleteAllRecipes() {
        try {
            return recipeDatabase.deleteAllRecipes();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return reloadRecipes();
    }

    /**
     * Update an existing recipe with the provided data.
     * @param data A map containing the recipe properties.
     * @return Result containing the updated recipe.
     */
    public Result<Record> updateRecipe(Map<String, String> data) {
        try {
            return recipeDatabase.updateRecipe(
                    Integer.parseInt(data.get("id")),
                    getStringOrNull(data.get("name")),
                    getStringOrNull(data.get("cuisine")),
                    getStringOrNull(data.get("category")),
                    getStringOrNull(data.get("instructions")),
                    parseIntegerOrNull(data.get("nutrition")),
                    getStringOrNull(data.get("cookingTime")),
                    getStringOrNull(data.get("ingredient"))
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Filter recipes based on provided criteria.
     * Only non-null or non-empty fields are included in the filter.
     * @param criteria A map containing the filter criteria.
     * @return The result after applying the filter.
     */
    public Result<Record> filterRecipes(Map<String, String> criteria) {
        try {
            return recipeDatabase.filterRecipes(
                    parseIntegerOrDefault(criteria.get("id"), 0),
                    getStringOrNull(criteria.get("name")),
                    getStringOrNull(criteria.get("cuisine")),
                    getStringOrNull(criteria.get("category")),
                    getStringOrNull(criteria.get("instructions")),
                    parseIntegerOrDefault(criteria.get("nutrition"), 0),
                    getStringOrNull(criteria.get("cookingTime")),
                    getStringOrNull(criteria.get("ingredient"))
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Utility: Convert an empty string to null.
     */
    private String getStringOrNull(String value) {
        return (value == null || value.trim().isEmpty()) ? null : value.trim();
    }

    /**
     * Utility: Parse an integer or return null if invalid.
     */
    private Integer parseIntegerOrNull(String value) {
        try {
            return value == null || value.trim().isEmpty() ? null : Integer.parseInt(value.trim());
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    /**
     * Utility: Parse an integer or return a default value if invalid.
     */
    private int parseIntegerOrDefault(String value, int defaultValue) {
        try {
            return value == null || value.trim().isEmpty() ? defaultValue : Integer.parseInt(value.trim());
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
    }

    /**
     * Set the DefaultTableModel for updating the view.
     */
    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }
}