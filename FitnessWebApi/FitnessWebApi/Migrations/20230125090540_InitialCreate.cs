using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

#pragma warning disable CA1814 // Prefer jagged arrays over multidimensional

namespace FitnessWebApi.Migrations
{
    /// <inheritdoc />
    public partial class InitialCreate : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "ActivityLevel",
                columns: table => new
                {
                    ActivityLevelID = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    ActivityLevelName = table.Column<string>(type: "nvarchar(64)", nullable: true),
                    DailyIntake = table.Column<short>(type: "smallint", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_ActivityLevel", x => x.ActivityLevelID);
                });

            migrationBuilder.CreateTable(
                name: "Gender",
                columns: table => new
                {
                    GenderID = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    GenderName = table.Column<string>(type: "nvarchar(48)", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Gender", x => x.GenderID);
                });

            migrationBuilder.CreateTable(
                name: "MealTime",
                columns: table => new
                {
                    MealTimeID = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    MealTimeName = table.Column<string>(type: "nvarchar(48)", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_MealTime", x => x.MealTimeID);
                });

            migrationBuilder.CreateTable(
                name: "Product",
                columns: table => new
                {
                    ProductID = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    ProductName = table.Column<string>(type: "nvarchar(96)", nullable: true),
                    ProductCode = table.Column<string>(type: "nvarchar(32)", nullable: true),
                    EnergyAmount = table.Column<double>(type: "float", nullable: false),
                    FatAmount = table.Column<double>(type: "float", nullable: false),
                    SaturatedFatAmount = table.Column<double>(type: "float", nullable: false),
                    CarbohydrateAmount = table.Column<double>(type: "float", nullable: false),
                    SugarAmount = table.Column<double>(type: "float", nullable: false),
                    FiberAmount = table.Column<double>(type: "float", nullable: false),
                    ProteinAmount = table.Column<double>(type: "float", nullable: false),
                    SaltAmount = table.Column<double>(type: "float", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Product", x => x.ProductID);
                });

            migrationBuilder.CreateTable(
                name: "User",
                columns: table => new
                {
                    UserID = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    Email = table.Column<string>(type: "nvarchar(64)", nullable: true),
                    Password = table.Column<string>(type: "nvarchar(255)", nullable: true),
                    FirstName = table.Column<string>(type: "nvarchar(32)", nullable: true),
                    LastName = table.Column<string>(type: "nvarchar(32)", nullable: true),
                    Height = table.Column<double>(type: "float", nullable: false),
                    GenderID = table.Column<int>(type: "int", nullable: false),
                    BirthdayDate = table.Column<DateTime>(type: "date", nullable: false),
                    CreatedAt = table.Column<DateTime>(name: "Created_At", type: "datetime2", nullable: false, defaultValueSql: "getdate()"),
                    ModifiedAt = table.Column<DateTime>(name: "Modified_At", type: "datetime2", nullable: false),
                    LastLogin = table.Column<DateTime>(name: "Last_Login", type: "datetime2", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_User", x => x.UserID);
                    table.ForeignKey(
                        name: "FK_User_Gender_GenderID",
                        column: x => x.GenderID,
                        principalTable: "Gender",
                        principalColumn: "GenderID",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "UserMeal",
                columns: table => new
                {
                    MealID = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    UserID = table.Column<int>(type: "int", nullable: false),
                    MealName = table.Column<string>(type: "nvarchar(64)", nullable: true),
                    PortionAmount = table.Column<double>(type: "float", nullable: false),
                    TotalTime = table.Column<DateTime>(type: "datetime2", nullable: false),
                    Instructions = table.Column<string>(type: "nvarchar(512)", nullable: true),
                    MealTimeID = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_UserMeal", x => x.MealID);
                    table.ForeignKey(
                        name: "FK_UserMeal_MealTime_MealTimeID",
                        column: x => x.MealTimeID,
                        principalTable: "MealTime",
                        principalColumn: "MealTimeID",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_UserMeal_User_UserID",
                        column: x => x.UserID,
                        principalTable: "User",
                        principalColumn: "UserID",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "UserPlan",
                columns: table => new
                {
                    UserPlanID = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    StartWeight = table.Column<double>(type: "float", nullable: false),
                    CurrentWeight = table.Column<double>(type: "float", nullable: false),
                    WeightGoal = table.Column<double>(type: "float", nullable: false),
                    ActivityLevelID = table.Column<int>(type: "int", nullable: false),
                    UserID = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_UserPlan", x => x.UserPlanID);
                    table.ForeignKey(
                        name: "FK_UserPlan_ActivityLevel_ActivityLevelID",
                        column: x => x.ActivityLevelID,
                        principalTable: "ActivityLevel",
                        principalColumn: "ActivityLevelID",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_UserPlan_User_UserID",
                        column: x => x.UserID,
                        principalTable: "User",
                        principalColumn: "UserID",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "UserRecipe",
                columns: table => new
                {
                    RecipeID = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    UserID = table.Column<int>(type: "int", nullable: false),
                    RecipeName = table.Column<string>(type: "nvarchar(128)", nullable: true),
                    PortionAmount = table.Column<double>(type: "float", nullable: false),
                    TotalTime = table.Column<DateTime>(type: "datetime2", nullable: false),
                    Instructions = table.Column<string>(type: "nvarchar(512)", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_UserRecipe", x => x.RecipeID);
                    table.ForeignKey(
                        name: "FK_UserRecipe_User_UserID",
                        column: x => x.UserID,
                        principalTable: "User",
                        principalColumn: "UserID",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "SizedProduct",
                columns: table => new
                {
                    SizedProductID = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    ServingSize = table.Column<double>(type: "float", nullable: false),
                    UserID = table.Column<int>(type: "int", nullable: false),
                    ProductID = table.Column<int>(type: "int", nullable: false),
                    UserMealMealID = table.Column<int>(type: "int", nullable: true),
                    UserRecipeRecipeID = table.Column<int>(type: "int", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_SizedProduct", x => x.SizedProductID);
                    table.ForeignKey(
                        name: "FK_SizedProduct_Product_ProductID",
                        column: x => x.ProductID,
                        principalTable: "Product",
                        principalColumn: "ProductID",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_SizedProduct_UserMeal_UserMealMealID",
                        column: x => x.UserMealMealID,
                        principalTable: "UserMeal",
                        principalColumn: "MealID");
                    table.ForeignKey(
                        name: "FK_SizedProduct_UserRecipe_UserRecipeRecipeID",
                        column: x => x.UserRecipeRecipeID,
                        principalTable: "UserRecipe",
                        principalColumn: "RecipeID");
                    table.ForeignKey(
                        name: "FK_SizedProduct_User_UserID",
                        column: x => x.UserID,
                        principalTable: "User",
                        principalColumn: "UserID",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.InsertData(
                table: "ActivityLevel",
                columns: new[] { "ActivityLevelID", "ActivityLevelName", "DailyIntake" },
                values: new object[,]
                {
                    { 1, "Female-Sedentary", (short)1800 },
                    { 2, "Female-Moderately", (short)2000 },
                    { 3, "Female-Active", (short)2400 },
                    { 4, "Male-Sedentary", (short)2200 },
                    { 5, "Male-Moderately", (short)2800 },
                    { 6, "Male-Active", (short)3200 }
                });

            migrationBuilder.InsertData(
                table: "Gender",
                columns: new[] { "GenderID", "GenderName" },
                values: new object[,]
                {
                    { 1, "Male" },
                    { 2, "Female" }
                });

            migrationBuilder.InsertData(
                table: "MealTime",
                columns: new[] { "MealTimeID", "MealTimeName" },
                values: new object[,]
                {
                    { 1, "Breakfast" },
                    { 2, "Lunch" },
                    { 3, "Dinner" },
                    { 4, "Snack" }
                });

            migrationBuilder.InsertData(
                table: "User",
                columns: new[] { "UserID", "BirthdayDate", "Created_At", "Email", "FirstName", "GenderID", "Height", "LastName", "Last_Login", "Modified_At", "Password" },
                values: new object[] { 1, new DateTime(2003, 1, 29, 0, 0, 0, 0, DateTimeKind.Unspecified), new DateTime(2023, 1, 25, 9, 5, 40, 64, DateTimeKind.Utc).AddTicks(2010), "example.com", "Nicklas", 1, 181.0, "Osbeck", new DateTime(2023, 1, 25, 9, 5, 40, 64, DateTimeKind.Utc).AddTicks(2012), new DateTime(2023, 1, 25, 9, 5, 40, 64, DateTimeKind.Utc).AddTicks(2012), "$2a$10$vNK0OYCLTz1BXR3a8Gs6weEz0w6l8LvDue5k3egq4WuyO3w9G7lvS" });

            migrationBuilder.CreateIndex(
                name: "IX_Product_ProductCode",
                table: "Product",
                column: "ProductCode",
                unique: true,
                filter: "[ProductCode] IS NOT NULL");

            migrationBuilder.CreateIndex(
                name: "IX_SizedProduct_ProductID",
                table: "SizedProduct",
                column: "ProductID");

            migrationBuilder.CreateIndex(
                name: "IX_SizedProduct_UserID",
                table: "SizedProduct",
                column: "UserID");

            migrationBuilder.CreateIndex(
                name: "IX_SizedProduct_UserMealMealID",
                table: "SizedProduct",
                column: "UserMealMealID");

            migrationBuilder.CreateIndex(
                name: "IX_SizedProduct_UserRecipeRecipeID",
                table: "SizedProduct",
                column: "UserRecipeRecipeID");

            migrationBuilder.CreateIndex(
                name: "IX_User_Email",
                table: "User",
                column: "Email",
                unique: true,
                filter: "[Email] IS NOT NULL");

            migrationBuilder.CreateIndex(
                name: "IX_User_GenderID",
                table: "User",
                column: "GenderID");

            migrationBuilder.CreateIndex(
                name: "IX_UserMeal_MealTimeID",
                table: "UserMeal",
                column: "MealTimeID");

            migrationBuilder.CreateIndex(
                name: "IX_UserMeal_UserID",
                table: "UserMeal",
                column: "UserID");

            migrationBuilder.CreateIndex(
                name: "IX_UserPlan_ActivityLevelID",
                table: "UserPlan",
                column: "ActivityLevelID");

            migrationBuilder.CreateIndex(
                name: "IX_UserPlan_UserID",
                table: "UserPlan",
                column: "UserID");

            migrationBuilder.CreateIndex(
                name: "IX_UserRecipe_UserID",
                table: "UserRecipe",
                column: "UserID");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "SizedProduct");

            migrationBuilder.DropTable(
                name: "UserPlan");

            migrationBuilder.DropTable(
                name: "Product");

            migrationBuilder.DropTable(
                name: "UserMeal");

            migrationBuilder.DropTable(
                name: "UserRecipe");

            migrationBuilder.DropTable(
                name: "ActivityLevel");

            migrationBuilder.DropTable(
                name: "MealTime");

            migrationBuilder.DropTable(
                name: "User");

            migrationBuilder.DropTable(
                name: "Gender");
        }
    }
}
