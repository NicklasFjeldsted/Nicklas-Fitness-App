namespace FitnessWebApi._Services
{
	public interface ISizedProductService
	{
		public Task<List<StaticSizedProductResponse>> GetAllById(int id);
		public Task<DirectSizedProductResponse> GetById(int id);
		public Task<DirectSizedProductResponse> Create(SizedProductRequest request);
		public Task<DirectSizedProductResponse> Update(int id, SizedProductRequest request);
		public Task<DirectSizedProductResponse> Delete(int id);
	}

	public class SizedProductService : ISizedProductService
	{
		private readonly ISizedProductRepository _repository;
		private readonly IMapper m_mapper;

		public SizedProductService(ISizedProductRepository repository, IMapper mapper)
		{
			_repository = repository;
			m_mapper = mapper;
		}

		public async Task<List<StaticSizedProductResponse>> GetAllById(int id)
		{
			List<SizedProduct> sizedProducts = await _repository.GetAllById(id);
			if(sizedProducts != null)
			{
				return sizedProducts.Select(sizedProduct => m_mapper.Map<SizedProduct, StaticSizedProductResponse>(sizedProduct)).ToList();
			}

			return null;
		}

		public async Task<DirectSizedProductResponse> GetById(int id)
		{
			SizedProduct sizedProduct = await _repository.GetById(id);

			if (sizedProduct != null)
			{
				return m_mapper.Map<DirectSizedProductResponse>(sizedProduct);
			}

			return null;
		}

		public async Task<DirectSizedProductResponse> Create(SizedProductRequest request)
		{
			SizedProduct sizedProduct = await _repository.Create(m_mapper.Map<SizedProduct>(request));
			if(sizedProduct != null)
			{
				return m_mapper.Map<DirectSizedProductResponse>(sizedProduct);
			}

			return null;
		}

		public async Task<DirectSizedProductResponse> Update(int id, SizedProductRequest request)
		{
			SizedProduct sizedProduct = await _repository.Update(id, m_mapper.Map<SizedProduct>(request));
			if (sizedProduct != null)
			{
				return m_mapper.Map<DirectSizedProductResponse>(sizedProduct);
			}

			return null;
		}

		public async Task<DirectSizedProductResponse> Delete(int id)
		{
			SizedProduct sizedProduct = await _repository.GetById(id);
			if (sizedProduct != null)
			{
				return m_mapper.Map<DirectSizedProductResponse>(await _repository.Delete(sizedProduct));
			}

			return null;
		}
	}
}
