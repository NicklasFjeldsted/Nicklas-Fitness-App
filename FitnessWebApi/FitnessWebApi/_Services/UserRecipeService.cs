namespace FitnessWebApi._Services
{
	public interface IUserRecipeService
	{
		public Task<List<StaticUserRecipeResponse>> GetAllById(int id);
		public Task<DirectUserRecipeResponse> GetById(int id);
		public Task<DirectUserRecipeResponse> Create(UserRecipeRequest request);
		public Task<DirectUserRecipeResponse> Update(int id, UserRecipeRequest request);
		public Task<DirectUserRecipeResponse> Delete(int id);
	}

	public class UserRecipeService : IUserRecipeService
	{
		private readonly IUserRecipeRepository _repository;
		private readonly IMapper m_mapper;

		public UserRecipeService(IUserRecipeRepository repository, IMapper mapper)
		{
			_repository = repository;
			m_mapper = mapper;
		}

		public async Task<List<StaticUserRecipeResponse>> GetAllById(int id)
		{
			List<UserRecipe> recipes = await _repository.GetAllById(id);
			if(recipes != null)
			{
				return recipes.Select(recipe => m_mapper.Map<UserRecipe, StaticUserRecipeResponse>(recipe)).ToList();
			}

			return null;
		}

		public async Task<DirectUserRecipeResponse> GetById(int id)
		{
			UserRecipe recipe = await _repository.GetById(id);
			if(recipe != null)
			{
				return m_mapper.Map<DirectUserRecipeResponse>(recipe);
			}

			return null;
		}

		public async Task<DirectUserRecipeResponse> Create(UserRecipeRequest request)
		{
			UserRecipe recipe = await _repository.Create(m_mapper.Map<UserRecipe>(request));
			if(recipe != null)
			{
				return m_mapper.Map<DirectUserRecipeResponse>(recipe);
			}

			return null;
		}

		public async Task<DirectUserRecipeResponse> Update(int id, UserRecipeRequest request)
		{
			UserRecipe recipe = await _repository.Update(id, m_mapper.Map<UserRecipe>(request));
			if(recipe != null)
			{
				return m_mapper.Map<DirectUserRecipeResponse>(recipe);
			}

			return null;
		}

		public async Task<DirectUserRecipeResponse> Delete(int id)
		{
			UserRecipe recipe = await _repository.GetById(id); 
			if(recipe != null)
			{
				return m_mapper.Map<DirectUserRecipeResponse>(await _repository.Delete(recipe));
			}

			return null;
		}
	}
}
