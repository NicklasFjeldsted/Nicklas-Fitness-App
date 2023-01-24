namespace FitnessWebApi._Repositories
{
	public interface IProductRepository
	{
		public Task<List<Product>> GetAll();
		public Task<Product> GetByBarCode(string barCode);
		public Task<Product> Create(Product request);
		public Task<Product> Update(string barCode, Product request);
		public Task<Product> Delete(Product product);

	}
	public class ProductRepository : IProductRepository
	{
		private readonly DatabaseContext _context;

		public ProductRepository(DatabaseContext context)
		{
			_context = context;
		}

		public async Task<List<Product>> GetAll()
		{
			return await _context.Product
				.AsNoTracking()
				.ToListAsync();
		}

		public async Task<Product> GetByBarCode(string barCode)
		{
			return await _context.Product
				.Where(x => x.ProductCode == barCode)
				.FirstOrDefaultAsync();
		}
		public async Task<Product> Create(Product request)
		{
			_context.Product.Add(request);
			await _context.SaveChangesAsync();
			return await GetByBarCode(request.ProductCode);
		}

		public async Task<Product> Update(string barCode, Product request)
		{
			Product product = await GetByBarCode(barCode);
			if(product != null)
			{
				product.ProductName = request.ProductName;
				product.EnergyAmount = request.EnergyAmount;
				product.FatAmount = request.FatAmount;
				product.SaturatedFatAmount = request.SaturatedFatAmount;
				product.CarbohydrateAmount = request.CarbohydrateAmount;
				product.SugarAmount = request.SugarAmount;
				product.ProteinAmount = request.ProteinAmount;
				product.SaltAmount = request.SaltAmount;
				product.FiberAmount = request.FiberAmount;

				await _context.SaveChangesAsync();
			}

			return product;
		}

		public async Task<Product> Delete(Product product)
		{
			_context.Product.Remove(product);
			await _context.SaveChangesAsync();
			return product;
		}
	}
}
