namespace FitnessWebApi._Controllers
{
	[Route("api/[controller]")]
	[ApiController]
	public class SizedProductController : ControllerBase
	{
		private readonly ISizedProductService _service;

		public SizedProductController(ISizedProductService service)
		{
			_service = service;
		}

		[HttpGet("{userId}")]
		public async Task<IActionResult> GetAllById(int userId)
		{
			try
			{
				List<StaticSizedProductResponse> sizedProducts = await _service.GetAllById(userId);
				if (sizedProducts == null)
				{
					return NotFound();
				}

				if (sizedProducts.Count == 0)
				{
					return NoContent();
				}


				return Ok(sizedProducts);
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
				DirectSizedProductResponse sizedProduct = await _service.GetById(id);
				if (sizedProduct == null)
				{
					return NotFound();
				}

				return Ok(sizedProduct);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpPost]
		public async Task<IActionResult> Create([FromBody] SizedProductRequest request)
		{
			try
			{
				DirectSizedProductResponse sizedProduct = await _service.Create(request);
				if (sizedProduct == null)
				{
					return BadRequest();
				}

				return Ok(sizedProduct);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpPut("{id}")]
		public async Task<IActionResult> Update(int id, [FromBody] SizedProductRequest request)
		{
			try
			{
				DirectSizedProductResponse sizedProduct = await _service.Update(id, request);
				if (sizedProduct == null)
				{
					return BadRequest();
				}

				return Ok(sizedProduct);
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
				DirectSizedProductResponse sizedProduct = await _service.Delete(id);
				if (sizedProduct == null)
				{
					return BadRequest();
				}

				return Ok(sizedProduct);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

	}
}
