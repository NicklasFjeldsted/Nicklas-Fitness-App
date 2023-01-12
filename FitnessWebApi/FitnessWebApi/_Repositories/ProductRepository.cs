namespace FitnessWebApi._Repositories
{
	public interface IProductInterface
	{
		public Task<List<Product>> GetAll();
		public Task<Product> GetByBarCode(string barCode);
		public Task<Product> Create(Product request);
		public Task<Product> Update(string barCode, Product request);
		public Task<Product> Delete(string barCode);

	}
	public class ProductRepository : IProductInterface
	{
		private readonly DatabaseContext _context;

		public ProductRepository(DatabaseContext context)
		{
			_context = context;
		}

		public async Task<List<Product>> GetAll()
		{
			return await _context.Product.ToListAsync();
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
			return await  GetByBarCode(request.ProductCode);
		}

		public async Task<Product> Update(string barCode, Product request)
		{
			throw new NotImplementedException();
		}

		public async Task<Product> Delete(string barCode)
		{
			throw new NotImplementedException();
		}
	}
}
