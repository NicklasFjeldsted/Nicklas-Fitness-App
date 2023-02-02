using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace FitnessWebApi._Controllers
{
	[Route("api/[controller]")]
	[ApiController]
	public class ProgressMealController : ControllerBase
	{
		private readonly IProgressMealService _service;

		public ProgressMealController(IProgressMealService service)
		{
			_service = service;
		}


		[HttpGet]
		public async Task<IActionResult> GetAll()
		{
			try
			{
				List<StaticProgressMealResponse> progressMeals = await _service.GetAll();
				if (progressMeals == null)
				{
					return Problem("Nothing was returned from service, this was unexpected");
				}

				if (progressMeals.Count == 0)
				{
					return NoContent();
				}


				return Ok(progressMeals);
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
				DirectProgressMealResponse progressMeal = await _service.GetById(id);
				if (progressMeal == null)
				{
					return NotFound();
				}

				return Ok(progressMeal);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpPost]
		public async Task<IActionResult> Create([FromBody] ProgressMealRequest request)
		{
			try
			{
				DirectProgressMealResponse progressMeal = await _service.Create(request);
				if (progressMeal == null)
				{
					return BadRequest();
				}

				return Ok(progressMeal);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpPut("{id}")]
		public async Task<IActionResult> Update(int id, [FromBody] ProgressMealRequest request)
		{
			try
			{
				DirectProgressMealResponse progressMeal = await _service.Update(id, request);
				if (progressMeal == null)
				{
					return BadRequest();
				}

				return Ok(progressMeal);
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
				DirectProgressMealResponse progressMeal = await _service.Delete(id);
				if (progressMeal == null)
				{
					return BadRequest();
				}

				return Ok(progressMeal);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}
	}
}
