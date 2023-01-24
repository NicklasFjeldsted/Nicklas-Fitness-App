namespace FitnessWebApi._Services
{
	public interface IUserMealService
	{
		public Task<List<StaticUserMealResponse>> GetAllById(int id);
		public Task<DirectUserMealResponse> GetById(int id);
		public Task<DirectUserMealResponse> Create(UserMealRequest request);
		public Task<DirectUserMealResponse> Update(int id, UserMealRequest request);
		public Task<DirectUserMealResponse> Delete(int id);
	}

	public class UserMealService : IUserMealService
	{
		private readonly IUserMealRepository _repository;
		private readonly IMapper m_mapper;

		public UserMealService(IUserMealRepository repository, IMapper mapper)
		{
			_repository = repository;
			m_mapper = mapper;
		}

		public async Task<List<StaticUserMealResponse>> GetAllById(int id)
		{
			List<UserMeal> meals = await _repository.GetAllById(id);
			if (meals != null)
			{
				return meals.Select(meal => m_mapper.Map<UserMeal, StaticUserMealResponse>(meal)).ToList();
			}

			return null;
		}

		public async Task<DirectUserMealResponse> GetById(int id)
		{
			UserMeal meal = await _repository.GetById(id);
			if (meal != null)
			{
				return m_mapper.Map<DirectUserMealResponse>(meal);
			}

			return null;
		}

		public async Task<DirectUserMealResponse> Create(UserMealRequest request)
		{
			UserMeal meal = await _repository.Create(m_mapper.Map<UserMeal>(request));
			if (meal != null)
			{
				return m_mapper.Map<DirectUserMealResponse>(meal);
			}

			return null;
		}

		public async Task<DirectUserMealResponse> Update(int id, UserMealRequest request)
		{
			UserMeal meal = await _repository.Update(id, m_mapper.Map<UserMeal>(request));
			if (meal != null)
			{
				return m_mapper.Map<DirectUserMealResponse>(meal);
			}

			return null;
		}

		public async Task<DirectUserMealResponse> Delete(int id)
		{
			UserMeal meal = await _repository.GetById(id);
			if (meal != null)
			{
				return m_mapper.Map<DirectUserMealResponse>(await _repository.Delete(meal));
			}

			return null;
		}
	}
}
