var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
#region Add services to the container
builder.Services.AddTransient<IProductRepository, ProductRepository>();
builder.Services.AddTransient<IProductService, ProductService>();

builder.Services.AddTransient<ISizedProductRepository, SizedProductRepository>();
builder.Services.AddTransient<ISizedProductService, SizedProductService>();

builder.Services.AddTransient<IUserRepository, UserRepository>();
builder.Services.AddTransient<IUserService, UserService>();

builder.Services.AddTransient<IUserMealRepository, UserMealRepository>();
builder.Services.AddTransient<IUserMealService, UserMealService>();

builder.Services.AddTransient<IUserRecipeRepository, UserRecipeRepository>();
builder.Services.AddTransient<IUserRecipeService, UserRecipeService>();
#endregion

builder.Services.AddDbContext<DatabaseContext>(options => {
	options.UseSqlServer(builder.Configuration.GetConnectionString("Default"));
});


builder.Services.AddControllers();

builder.Services.AddAutoMapper(typeof(Program));

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

if (app.Environment.IsDevelopment())
{
	app.UseSwagger();
	app.UseSwaggerUI();
}

app.UseCors(builder => {
    builder.SetIsOriginAllowed(option => true)
    .WithOrigins("http://localhost:4200")
    .AllowCredentials()
    .AllowAnyHeader()
    .AllowAnyMethod();
});

app.UseRouting();

// app.UseAuthentication
app.UseAuthorization();

app.MapControllers();

app.Run();
