namespace FitnessWebApi._Services
{
	public interface IUserPlanService
	{
		public Task<List<StaticUserPlanResponse>> GetAll();
		public Task<DirectUserPlanResponse> GetById(int id);
		public Task<DirectUserPlanResponse> Create(UserPlanRequest request);
		public Task<DirectUserPlanResponse> Update(int id, UserPlanRequest request);
		public Task<DirectUserPlanResponse> Delete(int id);
	}
	public class UserPlanService : IUserPlanService
	{
		public readonly IUserPlanRepository _repository;
		private readonly IMapper m_mapper;

		public UserPlanService(IUserPlanRepository repository, IMapper mapper)
		{
			_repository = repository;
			m_mapper = mapper;
		}

		public async Task<List<StaticUserPlanResponse>> GetAll()
		{
			List<UserPlan> userPlans = await _repository.GetAll();
			if(userPlans != null)
			{
				return userPlans.Select(userPlan => m_mapper.Map<UserPlan, StaticUserPlanResponse>(userPlan)).ToList();
			}

			return null;
		}

		public async Task<DirectUserPlanResponse> GetById(int id)
		{
			UserPlan userPlan = await _repository.GetById(id);
			if (userPlan != null)
			{
				return m_mapper.Map<DirectUserPlanResponse>(userPlan);
			}

			return null;
		}

		public async Task<DirectUserPlanResponse> Create(UserPlanRequest request)
		{
			UserPlan userPlan = await _repository.Create(m_mapper.Map<UserPlan>(request));
			if (userPlan != null)
			{
				return m_mapper.Map<DirectUserPlanResponse>(userPlan);
			}

			return null;
		}

		public async Task<DirectUserPlanResponse> Update(int id, UserPlanRequest request)
		{
			UserPlan userPlan = await _repository.Update(id, m_mapper.Map<UserPlan>(request));
			if (userPlan != null)
			{
				return m_mapper.Map<DirectUserPlanResponse>(userPlan);
			}

			return null;
		}

		public async Task<DirectUserPlanResponse> Delete(int id)
		{
			UserPlan userPlan = await _repository.GetById(id);
			if (userPlan != null)
			{
				return m_mapper.Map<DirectUserPlanResponse>(await _repository.Delete(userPlan));
			}

			return null;
		}
	}
}
