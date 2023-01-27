namespace FitnessWebApi._Services
{
	public interface IPlanProgressService
	{
		public Task<List<StaticPlanProgressResponse>> GetAll();
		public Task<DirectPlanProgressResponse> GetById(int id);
		public Task<DirectPlanProgressResponse> Create(PlanProgressRequest request);
		public Task<DirectPlanProgressResponse> Update(int id, PlanProgressRequest request);
		public Task<DirectPlanProgressResponse> Delete(PlanProgressRequest product);
	}

	public class PlanProgressService
	{
		private readonly IPlanProgressRepository _repository;
		private readonly IMapper m_mapper;

		public PlanProgressService(IPlanProgressRepository repository, IMapper mapper)
		{
			_repository = repository;
			m_mapper = mapper;
		}

		public async Task<List<StaticPlanProgressResponse>> GetAll()
		{
			List<PlanProgress> planProgresses = await _repository.GetAll();
			if(planProgresses != null)
			{
				return planProgresses.Select(planProgress => m_mapper.Map<PlanProgress, StaticPlanProgressResponse>(planProgress)).ToList();
			}

			return null;
		}

		public async Task<DirectPlanProgressResponse> GetById(int id)
		{
			PlanProgress planProgress = await _repository.GetById(id);
			if(planProgress != null)
			{
				return m_mapper.Map<DirectPlanProgressResponse>(planProgress);
			}

			return null;
		}

		public async Task<DirectPlanProgressResponse> Create(PlanProgressRequest request)
		{
			PlanProgress planProgress = await _repository.Create(m_mapper.Map<PlanProgress>(request));
		}

		public async Task<DirectPlanProgressResponse> Update(int id, PlanProgressRequest request);
		public async Task<DirectPlanProgressResponse> Delete(PlanProgressRequest product);
	}
}
