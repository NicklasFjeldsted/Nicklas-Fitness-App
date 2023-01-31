namespace FitnessWebApi._Controllers
{
	[Route("api/[controller]")]
	[ApiController]
	public class PlanProgressController : ControllerBase
	{
		private readonly IPlanProgressService _service;

		public PlanProgressController(IPlanProgressService service)
		{
			_service = service;
		}

		[HttpGet]
		public async Task<IActionResult> GetAll()
		{
			try
			{
				List<StaticPlanProgressResponse> planProgresses = await _service.GetAll();
				if (planProgresses == null)
				{
					return Problem("Nothing was returned from service, this was unexpected");
				}

				if (planProgresses.Count == 0)
				{
					return NoContent();
				}


				return Ok(planProgresses);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpGet("{id}")]
		public async Task<IActionResult> GetById(int id)
		{
			try
			{
				DirectPlanProgressResponse planProgress = await _service.GetById(id);
				if (planProgress == null)
				{
					return NotFound();
				}

				return Ok(planProgress);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpPost]
		public async Task<IActionResult> Create([FromBody] PlanProgressRequest request)
		{
			try
			{
				DirectPlanProgressResponse planProgress = await _service.Create(request);
				if (planProgress == null)
				{
					return BadRequest();
				}

				return Ok(planProgress);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpPut("{id}")]
		public async Task<IActionResult> Update(int id, [FromBody] PlanProgressRequest request)
		{
			try
			{
				DirectPlanProgressResponse planProgress = await _service.Update(id, request);
				if (planProgress == null)
				{
					return BadRequest();
				}

				return Ok(planProgress);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpDelete("{id}")]
		public async Task<IActionResult> Delete(int id)
		{
			try
			{
				DirectPlanProgressResponse planProgress = await _service.Delete(id);
				if (planProgress == null)
				{
					return BadRequest();
				}

				return Ok(planProgress);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}
	}
}
