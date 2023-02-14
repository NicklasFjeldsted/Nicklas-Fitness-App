namespace FitnessWebApi._Services
{
	public interface IPlanProgressService
	{
		public Task<List<StaticPlanProgressResponse>> GetAll();
		public Task<DirectPlanProgressResponse> GetById(int id);
		public Task<DirectPlanProgressResponse> Create(PlanProgressRequest request);
		public Task<DirectPlanProgressResponse> Update(int id, PlanProgressRequest request);
		public Task<DirectPlanProgressResponse> Delete(int id);
	}

	public class PlanProgressService : IPlanProgressService
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
			// CREATE A PLANPROGRESS WITH ALL FOUR MEALS AND ITS CORRESPONDING SIZEDPRODUCTS
			List<ProgressMeal> progressMeals = new List<ProgressMeal>(); // New list for all meals within request
			foreach(ProgressMealRequest meal in request.ProgressMeals) // foreach meal: breakfast, lunch, dinner, snacks
			{
				progressMeals.Add(m_mapper.Map<ProgressMeal>(meal)); // Add meal
				if(meal.SizedProducts.Count != 0) // if meal has any products
				{
					List<SizedProduct> products = new List<SizedProduct>(); // List to store mapped SizedProducts
					foreach(SizedProductRequest product in meal.SizedProducts) // For each SizedProduct in meal request. example oats in breakfast
					{
						products.Add(m_mapper.Map<SizedProduct>(product)); // Add to list
					}
					ProgressMeal updateMeal = progressMeals.FirstOrDefault(m => m.MealTimeID == meal.MealTimeID); // update current meal example breakfast
					updateMeal.SizedProducts = products; // sets it's sizedproduct to the mapped sizedproducts
					
				}
			}

			PlanProgress planProgress = m_mapper.Map<PlanProgress>(request);

			planProgress.ProgressMeals = progressMeals;

			PlanProgress response = await _repository.Create(planProgress); // CREATE PLAN

			if(response != null) // if created successfully
			{
				return m_mapper.Map<DirectPlanProgressResponse>(await _repository.GetById(response.PlanProgressID));
			}

			return null;
		}

		public async Task<DirectPlanProgressResponse> Update(int id, PlanProgressRequest request)
		{
			PlanProgress planProgress = await _repository.Update(id, m_mapper.Map<PlanProgress>(request));
			if(planProgress != null)
			{
				return m_mapper.Map<DirectPlanProgressResponse>(planProgress);
			}

			return null;
		}
		public async Task<DirectPlanProgressResponse> Delete(int id)
		{
			PlanProgress planProgress = await _repository.GetById(id);
			if(planProgress != null)
			{
				return m_mapper.Map<DirectPlanProgressResponse>(await _repository.Delete(planProgress));
			}

			return null;
		}
	}
}
