namespace FitnessWebApi._Services
{
	public interface IProgressMealService
	{
		public Task<List<StaticProgressMealResponse>> GetAll();
		public Task<DirectProgressMealResponse> GetById(int id);
		public Task<DirectProgressMealResponse> Create(ProgressMealRequest request);
		public Task<DirectProgressMealResponse> Update(int id, ProgressMealRequest request);
		public Task<DirectProgressMealResponse> Delete(int id);
	}

	public class ProgressMealService : IProgressMealService
	{
		private readonly IProgressMealRepository _repository;
		private readonly ISizedProductRepository _sizedProductRepository;
		private readonly IMapper m_mapper;

		public ProgressMealService(IProgressMealRepository repository, IMapper mapper, ISizedProductRepository sizedProductRepository)
		{
			_repository = repository;
			m_mapper = mapper;
			_sizedProductRepository = sizedProductRepository;
		}

		public async Task<List<StaticProgressMealResponse>> GetAll()
		{
			List<ProgressMeal> progressMeals = await _repository.GetAll();
			if(progressMeals != null)
			{
				return progressMeals.Select(progressMeal => m_mapper.Map<ProgressMeal, StaticProgressMealResponse>(progressMeal)).ToList();
			}

			return null;
		}

		public async Task<DirectProgressMealResponse> GetById(int id)
		{
			ProgressMeal progressMeal = await _repository.GetById(id);
			if(progressMeal != null)
			{
				return m_mapper.Map<DirectProgressMealResponse>(progressMeal);
			}

			return null;
		}

		public async Task<DirectProgressMealResponse> Create(ProgressMealRequest request)
		{
			List<SizedProduct> products = new List<SizedProduct>();
			foreach(SizedProductRequest product in request.SizedProducts)
			{
				products.Add(m_mapper.Map<SizedProduct>(product));
			}

			ProgressMeal progressMeal = m_mapper.Map<ProgressMeal>(request);

			progressMeal.SizedProducts = products;

			ProgressMeal response = await _repository.Create(progressMeal);

			if(response != null)
			{
				return m_mapper.Map<DirectProgressMealResponse>(await _repository.GetById(progressMeal.ProgressMealID));
			}

			return null;
		}

		public async Task<DirectProgressMealResponse> Update(int id, ProgressMealRequest request)
		{
			ProgressMeal progressMeal = await _repository.Update(id, m_mapper.Map<ProgressMeal>(request));
			if(progressMeal != null)
			{
				return m_mapper.Map<DirectProgressMealResponse>(progressMeal);
			}

			return null;
		}

		public async Task<DirectProgressMealResponse> Delete(int id)
		{
			ProgressMeal progressMeal = await _repository.GetById(id);
			if(progressMeal != null)
			{

				return m_mapper.Map<DirectProgressMealResponse>(await _repository.Delete(progressMeal));

			}
			return null;
		}
	}
}
