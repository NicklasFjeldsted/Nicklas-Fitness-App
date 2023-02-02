namespace FitnessWebApi._Repositories
{
	public interface ISizedProductRepository
	{
		public Task<List<SizedProduct>> GetAllById(int id);
		public Task<SizedProduct> GetById(int id);
		public Task<SizedProduct> Create(SizedProduct request);
		public Task<SizedProduct> Update(int id, SizedProduct request);
		public Task<SizedProduct> Delete(SizedProduct sizedProduct);
	}

	public class SizedProductRepository : ISizedProductRepository
	{
		private readonly DatabaseContext _context;

		public SizedProductRepository(DatabaseContext context)
		{
			_context = context;
		}

		public async Task<List<SizedProduct>> GetAllById(int id)
		{
			return await _context.SizedProduct
				.ToListAsync();
		}

		public async Task<SizedProduct> GetById(int id)
		{
			return await _context.SizedProduct
				.Include(x => x.Product)
				.Where(x => x.SizedProductID == id)
				.FirstOrDefaultAsync();
		}

		public async Task<SizedProduct> Create(SizedProduct request)
		{
			await _context.SizedProduct.AddAsync(request);
			await _context.SaveChangesAsync();
			return await GetById(request.SizedProductID);
		}

		public async Task<SizedProduct> Update(int id, SizedProduct request)
		{
			SizedProduct product = await GetById(id);
			if(product != null)
			{
				request.ServingSize = product.ServingSize;
				await _context.SaveChangesAsync();
			}

			return product;
		}

		public async Task<SizedProduct> Delete(SizedProduct sizedProduct)
		{
			_context.SizedProduct.Remove(sizedProduct);
			await _context.SaveChangesAsync();
			return sizedProduct;
		}

	}
}
